# Task Manager API

This repository contains the backend API for the Task Manager application.
The API is built with Java 17, Spring Boot 3 and follows a microservices architecture.
It provides functionalities for managing tasks, users, authentication/authorization,
notification service, project management, and more. The project uses Spring Cloud's
services for common microservice servers like gateway, discovery server, config server,
and so on.

This is a pet project of mine created for ***LEARNING PURPOSES*** , and it's currently under
development.

I have also built a [web front-end app](https://github.com/nturbo1/task-manager-front)
using typescript and react for this api.

## micro-services

- **user service**: user management.

- **task service:** task management.

- **api gateway:** serves as a single entry point for all the microservices,
  handles centralized routing, authentication/authorization, and load balancing.

- **service discovery server:** maintains a registry of available microservices
  and their instances.

- **config server:** stores application configuration files.

- **auth service:** provides custom auth service and interacts with the keycloak server.

## overall tech stack:

- **java 17**: core programming language
- **spring boot (v3.3.3):** backend framework for building microservices.
- **spring cloud gateway:** api gateway for routing and filtering requests.
- **spring cloud config**: central configurations storage server.
- **spring cloud netflix eureka:** service discovery and registration.
- **Spring Data (Jpa, MongoDB, Redis)**: Database interactions.
- **Spring Web:** Rest API, MVC, framework for building web apps.
- **MongoDB:** NoSQL database for storing user and task data.
- **Redis**: NoSQL database for storing user login session.
- **Mongock:** Database migration tool for MongoDB.
- **RabbitMQ:** Message Queue (async communication between the microservices).
- **Keycloak:** Authentication and authorization manager.
- **Docker:** Containerization for deployment and local development.
- **Testing:** JUnit 5, Mockito

## Prerequisites

- Java 17
- Maven
- Docker & Docker Compose

## Getting Started

Follow these steps to set up and run the application locally.

### 1. Clone the repository

```
git clone https://github.com/NTurbo1/task-manager.git
cd task-manager
```

### 2. Set up Docker containers

Start the required services (MongoDB, Redis, etc.) with Docker Compose.

```
docker-compose up -d
```

### 3. Configure environment variables

TODO: Should be updated...

### 4. Build and run the services

Build the individual microservices and start them.

```
mvn clean install
mvn spring-boot:run
```

### 5. Access the API

The services can be accessed via the API Gateway, typically running on
```http://localhost:8222```

## Docker Setup

A docker-compose.yml file is provided for local development. It sets up
the following services:

- MongoDB
- Redis
- RabbitMQ

To start all services with Docker, use ```docker-compose up```.

## API Documentation

This project uses Swagger/OpenAPI tools for api documentation.
After starting all the services, you can access the swagger ui on
```http://localhost:8222/swagger-ui.html```.

## Roles and Permissions Matrix

| Functionality | Admin | Project Manager |    Team Member    | Guest/Client |
|---------------|:-----:|:---------------:|:-----------------:|:------------:|
| User CRUD     |   +   |        -        |         -         |      -       |
| Project CRUD  |   +   |        +        |    ONLY: Read     |  ONLY: Read  |
| Task CREATE   |   +   |        +        |         -         |      -       |
| Task READ     |   +   |        +        |         +         |      -       |
| Task UPDATE   |   +   |        +        | ONLY: task status |      -       |
| Task DELETE   |   +   |        +        |         -         |      -       |


