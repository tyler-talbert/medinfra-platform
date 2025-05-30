# 🧠 Microservices Platform – End-to-End System (Spring Boot + Kafka + gRPC)

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
- Kafka + Zookeeper for event streams
- MySQL for relational data storage
- LocalStack for simulated AWS (VPC, ECS, MSK)

## 🛠️ Tech Stack

- **Languages & Frameworks:** Java 17, Spring Boot 3, Spring Cloud Gateway, gRPC
- **Messaging:** Apache Kafka
- **Auth:** JWT (JSON Web Tokens)
- **Containerization:** Docker, Docker Compose
- **Database:** MySQL
- **Cloud Simulation:** LocalStack (for AWS CloudFormation, MSK, VPC)
- **Docs:** OpenAPI (Swagger)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/tyler-talbert/medinfra-platform.git
cd patient-management
```

### 2. Start the Application

```bash
docker-compose up --build
```

All services will start in isolated containers. Kafka, MySQL, and microservices will be accessible via internal Docker networking.

> ⚠️ Prerequisites: Make sure Docker and Docker Compose are installed and running on your machine.

---

## 📫 How to Interact

### API Endpoints via Gateway

| Service | Route | Method | Description |
|--------|-------|--------|-------------|
| Patient | `/api/patients` | `GET, POST, PUT, DELETE` | Manage patient records |
| Billing | `/api/billing` | `POST` | Trigger billing workflows |
| Auth | `/api/auth/login` | `POST` | Get JWT token for access |
| Analytics | Kafka-only | `N/A` | Consumes events in background |

> Access Swagger UI per service at: `http://localhost:<PORT>/swagger-ui.html` (configured in each service)

---

## 📊 Key Features

- ✅ Secure JWT-based authentication and API Gateway routing
- 🧵 gRPC for low-latency internal calls
- 🔁 Kafka for event-driven communication
- 🐬 MySQL for persistent storage
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



