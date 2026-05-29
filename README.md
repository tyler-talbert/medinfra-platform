# Patient Management Platform

Containerized microservices backend simulating a healthcare system. Built to practice service-to-service patterns I don't get to touch much at work: gRPC, Kafka, JWT-secured gateway routing, and AWS infra emulated locally with LocalStack.

## Architecture

Five services behind an API gateway:

- **api-gateway** — Spring Cloud Gateway, JWT validation filter, routes to downstream services
- **auth-service** — issues and validates JWT tokens
- **patient-service** — patient CRUD, exposes both REST (through the gateway) and gRPC for internal callers
- **billing-service** — produces billing events to Kafka
- **analytics-service** — consumes those events and runs analytics

Communication: REST inbound through the gateway, gRPC between services where latency matters, Kafka for anything event-driven.

Infra is Docker Compose locally. LocalStack stands in for AWS (VPC, ECS, MSK) so the CloudFormation templates can be exercised without spending money.

## Stack

Java 17, Spring Boot 3, Spring Cloud Gateway, gRPC, Kafka, PostgreSQL, JWT, Docker Compose, LocalStack, OpenAPI/Swagger.

## Layout

```
api-gateway/        # routes + JWT filter
auth-service/       # login, token issuance
patient-service/    # gRPC server + REST
billing-service/    # Kafka producer
analytics-service/  # Kafka consumer
proto/              # shared .proto definitions
docker-compose.yml
```

## Running it

```bash
git clone https://github.com/tyler-talbert/medinfra-platform.git
cd medinfra-platform
docker-compose up --build
```

Needs Docker and Docker Compose. Everything (services, Kafka, Postgres) runs in containers on the internal Docker network.

## Endpoints

All routed through the gateway on `:4004`.

| Path             | Method                 | Description                |
| ---------------- | ---------------------- | -------------------------- |
| `/auth/login`    | POST                   | Returns a JWT              |
| `/api/patients`  | GET, POST, PUT, DELETE | Patient CRUD, JWT required |
| `/api/billing`   | POST                   | Triggers billing workflow  |

Swagger per service:

- Auth: `http://localhost:4004/swagger-ui/index.html?url=/api-docs/auth`
- Patient: `http://localhost:4004/swagger-ui/index.html?url=/api-docs/patients`

## Gateway routes

| Gateway path           | Target                  | Methods                | Notes                          |
| ---------------------- | ----------------------- | ---------------------- | ------------------------------ |
| `/api/patients/**`     | `patient-service:4000`  | GET, POST, PUT, DELETE | JWT required                   |
| `/auth/**`             | `auth-service:4005`     | POST                   | Login, issues JWT              |
| `/api-docs/patients`   | `patient-service:4000`  | GET                    | Rewrites to `/v3/api-docs`     |
| `/api-docs/auth`       | `auth-service:4005`     | GET                    | Rewrites to `/v3/api-docs`     |

JWT tokens come from `/auth/login` and get validated by a custom `JwtValidation` filter on the gateway.

## Integration test example

```java
@Test
public void shouldReturnPatientsWithValidToken() {
    String token = given()
        .contentType("application/json")
        .body("{\"email\": \"testuser@test.com\", \"password\": \"password123\"}")
        .when()
        .post("/auth/login")
        .then()
        .statusCode(200)
        .extract()
        .jsonPath()
        .get("token");

    given()
        .header("Authorization", "Bearer " + token)
        .when()
        .get("/api/patients")
        .then()
        .statusCode(200)
        .body("patients", notNullValue());
}
```

Exercises the login flow, JWT propagation, gateway filter, and protected route in one shot.

## Possible next steps

- Prometheus + Grafana for metrics
- Flyway for DB migrations
- TLS + SASL on Kafka
- Kubernetes + Helm
- GitHub Actions for CI/CD

## Author

Tyler Talbert — [LinkedIn](https://www.linkedin.com/in/tylertal)
