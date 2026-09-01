<div align="center">

# 🏦 Enterprise Banking API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=flat&logo=springboot&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=flat)
![Status](https://img.shields.io/badge/Deployed-Docker-2496ED?style=flat&logo=docker&logoColor=white)

*Enterprise-grade banking API with JWT authentication, role-based access control, and transaction management*

</div>

---

## ✨ Features

- JWT Authentication & Authorization
- Role-based Access Control (USER, ADMIN, MANAGER)
- Account Management (create, view, transfer)
- Transaction History with Pagination
- Redis Caching for Performance
- PostgreSQL with Flyway Migrations
- OpenAPI/Swagger Documentation
- Comprehensive Test Suite
- Docker Containerization

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=flat&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-24-2496ED?style=flat&logo=docker&logoColor=white)

## 🚀 Quick Start

```bash
# Clone repository
git clone https://github.com/Raphasha27/java-enterprise-banking-api.git
cd java-enterprise-banking-api

# Start database services
docker-compose up -d postgres redis

# Run application
./mvnw spring-boot:run

# Access API documentation
open http://localhost:8080/swagger-ui.html
```

### Docker Deployment

```bash
docker-compose up --build
```

## 📡 API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/v1/auth/register` | Register new user | No |
| `POST` | `/api/v1/auth/login` | Login user | No |
| `POST` | `/api/v1/accounts` | Create account | Yes |
| `GET` | `/api/v1/accounts` | List accounts | Yes |
| `GET` | `/api/v1/accounts/{id}` | Get account | Yes |
| `POST` | `/api/v1/accounts/{id}/transfer` | Transfer funds | Yes |
| `GET` | `/api/v1/accounts/{id}/balance` | Get balance | Yes |
| `GET` | `/api/v1/transactions` | List transactions | Yes |

## 🏗️ Architecture

```
┌─────────────────┐
│   Client        │
└────────┬────────┘
         │
┌────────▼────────┐
│   Spring Boot   │
│   REST API      │
└────────┬────────┘
         │
┌────────▼────────┐
│   Security      │
│   (JWT/OAuth)   │
└────────┬────────┘
         │
┌────────▼────────┐
│   Service Layer │
└────────┬────────┘
         │
┌────────▼────────┐
│   PostgreSQL    │
│   + Redis       │
└─────────────────┘
```

## 🌐 Live Demo

| Platform | URL |
|----------|-----|
| GitHub Pages | [raphasha27.github.io/java-enterprise-banking-api](https://raphasha27.github.io/java-enterprise-banking-api) |
| Docker Hub | [hub.docker.com/r/raphasha27/java-enterprise-banking-api](https://hub.docker.com/r/raphasha27/java-enterprise-banking-api) |

## 👤 Author

**raphasha27** — [GitHub](https://github.com/raphasha27)
