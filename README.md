# Project: Task Manager

## Services
- **User service**:
  - **Responsibilities:** Manages user registration, authentication/authorization, and profile management.

- **Task service:**
  - **Responsibilities:** Handles creation, updating, deletion, and management of tasks.

- **Notification service:**
  - **Responsibilities:** Sends notifications to users about upcoming tasks, deadlines, and project updates.

- **Analytics service:**
  - **Responsibilities:** Provides insights and analytics about user productivity, task completion rates, and project progress.

- **API Gateway:**
  - **Responsibilities:** Serves as a single entry point for all the microservices, handles routing, authentication, and rate limiting.

- **Service discovery server:**
  - **Responsibilities:** Maintains a registry of available microservices and their instances.

## Overall tech Stack:
- **Backend:** Java 17, Spring Boot 3, Spring Cloud, Spring Data Jpa, Spring Data MongoDB.
- **Database:** PostgreSQL, MongoDB, Redis 
- **Testing:** JUnit 5, Mockito
- **Deployment:** Docker, Kubernetes