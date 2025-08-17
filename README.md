# User Service

A Spring Boot WebFlux microservice for managing user data with MongoDB persistence and reactive programming support.

## Overview

This is a reactive Spring Boot application built using Clean Architecture principles. It provides a REST API for creating and managing user data with the following key features:

- **Reactive Programming**: Built with Spring WebFlux for non-blocking I/O operations
- **Clean Architecture**: Separates business logic from infrastructure concerns
- **MongoDB Integration**: Uses MongoDB for data persistence with embedded MongoDB for development
- **Docker Support**: Containerized application with multi-stage Docker build
- **Kubernetes Ready**: Includes Kubernetes deployment configurations
- **API Documentation**: Integrated OpenAPI/Swagger documentation

## Architecture

The application follows Clean Architecture principles with the following layers:

```
├── core/                    # Business Logic Layer
│   ├── model/              # Domain entities
│   └── usecase/            # Business use cases
├── infrastructure/         # Infrastructure Layer
│   ├── port/              # Adapters for external systems
│   └── repository/        # Data access layer
└── presenter/             # Presentation Layer
    ├── handler/           # Request handlers
    └── router/            # Route configurations
```

### Key Components

- **User Model**: Core domain entity representing a user with id, firstName, and lastName
- **UserPort**: Infrastructure adapter that implements business use cases
- **UserHandler**: Handles HTTP requests and responses
- **UserRouter**: Defines API routes and endpoints
- **UserRepository**: MongoDB data access interface

## Technology Stack

- **Java 22**: Latest Java LTS version
- **Spring Boot 3.3.2**: Main application framework
- **Spring WebFlux**: Reactive web framework
- **Spring Data MongoDB**: Database integration
- **MongoDB**: NoSQL database for data storage
- **Embedded MongoDB**: For development and testing
- **Lombok**: Reduces boilerplate code
- **SpringDoc OpenAPI**: API documentation
- **Logback**: Logging framework with JSON support
- **Maven**: Build and dependency management
- **Docker**: Containerization
- **Kubernetes**: Container orchestration

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 22** or later
- **Maven 3.6+**
- **Docker** (optional, for containerized deployment)
- **Kubernetes** (optional, for K8s deployment)

## Setup and Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd user-service
```

### 2. Local Development Setup

#### Option A: Using Maven (Recommended for Development)

1. **Install Dependencies**:
   ```bash
   ./mvnw clean install
   ```

2. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Verify the Application**:
   The application will start on port 8080. You can verify it's running by accessing:
   - Health check: `http://localhost:8080/actuator/health`
   - API documentation: `http://localhost:8080/swagger-ui.html`

#### Option B: Using Docker

1. **Build Docker Image**:
   ```bash
   docker build -t user-service:latest .
   ```

2. **Run Container**:
   ```bash
   docker run -p 8080:8080 user-service:latest
   ```

### 3. Production Deployment

#### Docker Deployment

1. **Build and Push Image**:
   ```bash
   docker build -t your-registry/user-service:1.0.0 .
   docker push your-registry/user-service:1.0.0
   ```

2. **Update Kubernetes Configuration**:
   Edit `k8s/user-service.yaml` and update the image reference:
   ```yaml
   image: your-registry/user-service:1.0.0
   ```

#### Kubernetes Deployment

1. **Deploy to Kubernetes**:
   ```bash
   kubectl apply -f k8s/user-service.yaml
   ```

2. **Verify Deployment**:
   ```bash
   kubectl get pods
   kubectl get services
   ```

3. **Access the Service**:
   ```bash
   kubectl port-forward service/user-service 8080:80
   ```

## API Documentation

### Endpoints

#### Create User
- **URL**: `POST /api/user`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe"
  }
  ```
- **Response**:
  ```json
  "Hello John Doe"
  ```

### Testing the API

#### Using cURL

```bash
curl -X POST http://localhost:8080/api/user \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe"
  }'
```

#### Using HTTPie

```bash
http POST localhost:8080/api/user firstName=John lastName=Doe
```

## Configuration

### Application Properties

The application can be configured using the following files:

- `src/main/resources/application.yaml`: Main configuration
- `src/main/resources/application-dev.yaml`: Development-specific configuration

### Key Configuration Properties

```yaml
spring:
  application:
    name: user-service
  webflux:
    base-path: api
  data:
    mongodb:
      port: 27017
      database: userdb

de:
  flapdoodle:
    mongodb:
      embedded:
        version: "5.0.5"
```

### Environment Variables

For production deployment, you can override configuration using environment variables:

- `SPRING_DATA_MONGODB_URI`: MongoDB connection string
- `SPRING_DATA_MONGODB_DATABASE`: Database name
- `PORT`: Application port (default: 8080)

## Development

### Running Tests

```bash
./mvnw test
```

### Code Style

The project uses Lombok to reduce boilerplate code. Ensure your IDE has Lombok plugin installed.

### Logging

The application uses Logback with JSON formatting for structured logging. Logs include:
- User creation events
- MongoDB operations
- Request/response information

## Monitoring and Health Checks

### Health Endpoints

- **Health Check**: `GET /actuator/health`
- **Application Info**: `GET /actuator/info`

### API Documentation

Access the interactive API documentation at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`

## Troubleshooting

### Common Issues

1. **Port Already in Use**:
   ```bash
   # Kill process using port 8080
   lsof -ti:8080 | xargs kill -9
   ```

2. **MongoDB Connection Issues**:
   - Ensure MongoDB is running (embedded MongoDB should start automatically)
   - Check MongoDB logs in application output

3. **Build Issues**:
   ```bash
   # Clean and rebuild
   ./mvnw clean install -U
   ```

### Debug Mode

Run the application in debug mode:
```bash
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team or create an issue in the repository.
