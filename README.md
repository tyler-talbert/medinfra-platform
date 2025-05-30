# 🧠 Microservices Platform – Patient Management System (Spring Boot + Kafka + gRPC + Docker)

## Overview

This project is a fully containerized, production-style microservices platform built from scratch. It simulates a healthcare system with secure API management, service-to-service communication via gRPC, asynchronous processing via Kafka, and infrastructure provisioning with AWS tools (emulated locally). Designed to demonstrate real-world architecture patterns and backend infrastructure.

## ⚙️ Architecture

**Microservices:**

- **API Gateway** – Centralized routing and JWT propagation
- **Auth Service** – Issues and validates JWT tokens
- **Patient Service** – CRUD operations for patient records
- **Billing Service** – Publishes events for payment workflows
- **Analytics Service** – Consumes Kafka topics and processes analytical insights

**Inter-Service Communication:**

- REST (API Gateway → downstream services)
- gRPC (internal service-to-service calls)
- Kafka (event-driven message processing)

**Infrastructure & Tools:**

- Docker Compose for orchestration
- Kafka for event streams
- PostgreSQL for relational data storage
- LocalStack for simulated AWS (VPC, ECS, MSK)

## 🛠️ Tech Stack

- **Languages & Frameworks:** Java 17, Spring Boot 3, Spring Cloud Gateway, gRPC
- **Messaging:** Apache Kafka
- **Auth:** JWT (JSON Web Tokens)
- **Containerization:** Docker, Docker Compose
- **Database:** PostgreSQL
- **Cloud Simulation:** LocalStack (for AWS CloudFormation, MSK, VPC)
- **Docs:** OpenAPI (Swagger)

## 📁 Project Structure

- `/api-gateway` – Spring Cloud Gateway routes + JWT filter
- `/auth-service` – Login + token issuance
- `/patient-service` – gRPC server + REST exposure
- `/billing-service` – Kafka producer (billing events)
- `/analytics-service` – Kafka consumer (analytics)
- `/proto` – Shared .proto definitions for gRPC
- `/docker-compose.yml` – Spin up entire stack locally


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/tyler-talbert/medinfra-platform.git
cd medinfra-platform
```

### 2. Start the Application

```bash
docker-compose up --build
```

All services will start in isolated containers. All services—including Kafka, PostgreSQL, and internal APIs—are accessible via Docker service names through the internal network.

> ⚠️ Prerequisites: Make sure Docker and Docker Compose are installed and running on your machine.

---

## 📫 How to Interact

### API Endpoints via Gateway

| Service | Route | Method | Description |
|--------|-------|--------|-------------|
| Patient | `/api/patients` | `GET, POST, PUT, DELETE` | Manage patient records |
| Billing | `/api/billing` | `POST` | Trigger billing workflows |
| Auth | `/api/auth/login` | `POST` | Get JWT token for access |


> Access Swagger UI per service via the Gateway:

- [Auth Service Swagger](http://localhost:4004/swagger-ui/index.html?url=/api-docs/auth)
- [Patient Service Swagger](http://localhost:4004/swagger-ui/index.html?url=/api-docs/patients)

---

🧪 Integration Testing with Secured API Gateway Routes
This platform uses Spring Cloud Gateway to proxy and secure access to internal microservices via JWT authentication. Routes are exposed through Docker container names and include built-in request filtering and documentation access.

### 🔐 API Gateway Routes

| Gateway Path        | Target Service              | Method(s)               | Description                                    |
|---------------------|-----------------------------|--------------------------|------------------------------------------------|
| `/api/patients/**`  | `patient-service:4000`      | GET, POST, PUT, DELETE   | Protected patient CRUD API (requires JWT)      |
| `/auth/**`          | `auth-service:4005`         | POST                     | Login endpoint (returns JWT)                   |
| `/api-docs/patients` | `patient-service:4000` | GET | Rewrites to `/v3/api-docs` internally |
| `/api-docs/auth`     | `auth-service:4005`    | GET | Rewrites to `/v3/api-docs` internally |


🔁 JWT tokens are issued via /auth/login and validated by a custom JwtValidation Gateway filter.

✅ Example Integration Flow

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
This test verifies:

JWT login flow works as expected

Authenticated users can access protected routes

Gateway routes and filters are correctly configured

### 📖 Swagger UI Access

Interactive Swagger docs are available through the API Gateway (rewrite paths in place):

Patient Service: http://localhost:4004/swagger-ui/index.html?url=/api-docs/patients

Auth Service: http://localhost:4004/swagger-ui/index.html?url=/api-docs/auth


---

## 📊 Key Features

- ✅ Secure JWT-based authentication and API Gateway routing
- 🧵 gRPC for low-latency internal calls
- 🔁 Kafka for event-driven communication
- 🐬 PostgreSQL for persistent storage
- ☁️ LocalStack to simulate AWS infrastructure provisioning (ECS, VPC, MSK)
- 🐳 Docker Compose to spin up all services in seconds

---

## 🎓 What I Learned

- Microservice orchestration with real production tools
- Best practices for Spring Boot 3 + gRPC integration
- Kafka message publishing, consumption, and topic design
- Docker Compose for multi-container deployment
- Infrastructure-as-code basics using AWS CloudFormation

---

## 📈 Future Improvements

- Add Prometheus + Grafana for metrics and alerting
- Integrate Flyway for DB versioning
- Secure Kafka with TLS + SASL authentication
- Use Kubernetes + Helm for deployment scalability
- Integrate CI/CD pipeline via GitHub Actions

---

## 👨‍💻 Author

**Tyler Talbert** – Senior Software Engineer @ Visa  
[LinkedIn](www.linkedin.com/in/tylertal)

---

## 🏷️ Tags

`#microservices` `#springboot` `#grpc` `#kafka` `#jwt` `#docker` `#localstack` `#cloudformation` `#backend-architecture`



