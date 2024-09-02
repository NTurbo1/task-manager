# Project: Task Manager

## Services
- [ ] **User service**:
  - **Responsibilities:** Manages user registration and profile management.

- [ ] **Task service:**
  - **Responsibilities:** Handles creation, updating, deletion, and management of tasks.

- [ ] **Notification service:**
  - **Responsibilities:** Sends notifications to users about upcoming tasks, deadlines, and project updates.

- [ ] **Analytics service:**
  - **Responsibilities:** Provides insights and analytics about user productivity, task completion rates, and project progress.

- [ ] **API Gateway server:**
  - **Responsibilities:** Serves as a single entry point for all the microservices, handles routing, authentication/authorization, load balancing, and rate limiting.

- [x] **Service discovery server:**
  - **Responsibilities:** Maintains a registry of available microservices and their instances.

- [x] **Config server:**
  - **Responsibilities:** Stores application configuration files and keeps track of their versions.

## Overall tech Stack:
- **Backend:** Java 17, Spring Boot 3, Spring Cloud, Spring Data Jpa, Spring Data MongoDB, Spring Web.
- **Database:** PostgreSQL, MongoDB, Redis 
- **Testing:** JUnit 5, Mockito
- **Deployment:** Docker, Kubernetes