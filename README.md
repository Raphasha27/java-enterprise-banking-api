# Enterprise Banking API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7-DC382D?style=flat&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-24-2496ED?style=flat&logo=docker&logoColor=white)

> Enterprise-grade banking API with JWT authentication, role-based access control, and transaction management

## Architecture

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

## Features

- JWT Authentication & Authorization
- Role-based Access Control (USER, ADMIN, MANAGER)
- Account Management (create, view, transfer)
- Transaction History with Pagination
- Redis Caching for Performance
- PostgreSQL with Flyway Migrations
- OpenAPI/Swagger Documentation
- Comprehensive Test Suite
- Docker Containerization

## Quick Start

### Prerequisites
- Java 21+
- PostgreSQL 16+
- Redis 7+
- Docker (optional)

### Local Development

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

## API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/v1/auth/register` | Register new user | No |
| POST | `/api/v1/auth/login` | Login user | No |
| POST | `/api/v1/accounts` | Create account | Yes |
| GET | `/api/v1/accounts` | List accounts | Yes |
| GET | `/api/v1/accounts/{id}` | Get account | Yes |
| POST | `/api/v1/accounts/{id}/transfer` | Transfer funds | Yes |
| GET | `/api/v1/accounts/{id}/balance` | Get balance | Yes |
| GET | `/api/v1/transactions` | List transactions | Yes |

## Usage Example

```bash
# Register user
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "password123", "name": "John Doe"}'

# Login
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "password123"}'

# Create account (with JWT token)
curl -X POST http://localhost:8080/api/v1/accounts?initialBalance=1000 \
  -H "Authorization: Bearer <token>"

# Transfer funds
curl -X POST "http://localhost:8080/api/v1/accounts/{id}/transfer?toAccountId={id}&amount=100" \
  -H "Authorization: Bearer <token>"
```

## Project Structure

```
java-enterprise-banking-api/
├── src/
│   ├── main/
│   │   ├── java/com/banking/
│   │   │   ├── BankingApplication.java
│   │   │   ├── config/           # Configuration classes
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── model/            # JPA entities
│   │   │   ├── repository/       # Data repositories
│   │   │   ├── service/          # Business logic
│   │   │   ├── security/         # JWT security
│   │   │   └── exception/        # Exception handling
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/migration/     # Flyway migrations
│   └── test/                     # Test suite
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DATABASE_URL` | PostgreSQL URL | `jdbc:postgresql://localhost:5432/banking` |
| `REDIS_HOST` | Redis host | `localhost` |
| `JWT_SECRET` | JWT secret key | Generated on startup |
| `JWT_EXPIRATION` | Token expiration (ms) | `86400000` |

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing`)
5. Open a Pull Request

## License

MIT License - see [LICENSE](LICENSE) for details

## Live Demo

| Platform | URL |
|----------|-----|
| GitHub Pages | [https://raphasha27.github.io/java-enterprise-banking-api](https://raphasha27.github.io/java-enterprise-banking-api) |
| Docker Hub | [docker pull raphasha27/java-enterprise-banking-api](https://hub.docker.com/r/raphasha27/java-enterprise-banking-api) |

