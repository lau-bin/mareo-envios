# Mareo Envíos

Welcome to **Mareo Envíos**, a Spring Boot application designed to provide a digital solution for efficient, country-wide shipping services. This application exposes a RESTful API for managing shipments, designed to help businesses streamline logistics with ease.

## Project Overview

**Mareo Envíos** was created to address inefficiencies in traditional shipping companies. This application provides a robust, scalable web service to facilitate merchandise shipments across the country. The project employs modern technologies and practices for maintainability and scalability.

## Tech Stack

- **Language:** Java 11
- **Framework:** Spring Boot 2.7.18
- **Database:** PostgreSQL
- **Dependency Management:** Maven
- **API Documentation:** Swagger (via `springdoc-openapi`)
- **Containerization:** Docker with multi-stage builds, Docker Compose

## Features

- **RESTful API:** Provides JSON-based endpoints for all CRUD operations.
- **Database Integration:** Utilizes PostgreSQL for reliable data storage and retrieval.
- **Caching and Resilience:** Incorporates Caffeine for caching and Resilience4J for fault tolerance.
- **Testing:** Includes unit and integration tests powered by Testcontainers for database isolation.
- **Documentation:** API documentation generated with Swagger and Asciidoctor.

## Prerequisites

- Java 11
- Docker and Docker Compose
- Maven 3.6+
- PostgreSQL

## Project Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/lau-bin/mareo-envios.git
   cd mareo-envios
   ```

2. **Build the Project**
   ```bash
   mvn clean package
   ```

3. **Run Docker Compose**
   Ensure Docker is running, then:
   ```bash
   docker-compose up -d
   ```

4. **Access API Documentation**
   The API documentation will be available at `http://localhost:8080/swagger-ui.html`.

## Testing

To run unit and integration tests:

```bash
mvn verify
```

## Docker Configuration

### Dockerfile

The Docker setup includes a multi-stage build:

- **Builder Stage:** Compiles and packages the application.
- **Final Stage:** Copies the built application to a smaller, optimized image.

### Docker Compose

`docker-compose.yml` includes services for:

- **Application:** Runs the Mareo Envíos API.
- **Database:** Sets up a PostgreSQL instance.

## API Documentation

The application uses `springdoc-openapi` to generate API documentation:

- **Swagger UI:** Available at `http://localhost:8080/swagger-ui.html`.
- **OpenAPI JSON Spec:** Generated and saved under `target/openapi-docs.json` for further integration or API client generation.

## Profiles

### `generate_doc`
Generates API documentation in HTML and PDF format using Asciidoctor.

### `generate_api_helpers`
Generates API clients and helper libraries for TypeScript (via `openapi-generator-maven-plugin`) to integrate easily with front-end applications.

## Contributing

Contributions are welcome. Please open issues or submit pull requests for any suggestions, bugs, or feature requests.

## License

This project is licensed under the MIT License. See [LICENSE](https://opensource.org/licenses/MIT) for details. 

---

**Developed by Lautaro Cutri**
