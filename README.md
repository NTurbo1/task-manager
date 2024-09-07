# Project: Task Manager

## Services
- [x] **User service**:
  - **Responsibilities:** Manages user data CRUD and profile management.

- [x] **Task service:**
  - **Responsibilities:** Handles creation, updating, deletion, and management of tasks.

- [ ] **Notification service:**
  - **Responsibilities:** Sends notifications to users about upcoming tasks, deadlines, and project updates.

- [ ] **Analytics service:**
  - **Responsibilities:** Provides insights and analytics about user productivity, task completion rates, and project progress.

- [x] **API Gateway server:**
  - **Responsibilities:** Serves as a single entry point for all the microservices, handles routing, 
                          authentication/authorization, load balancing, and rate limiting.

- [x] **Service discovery server:**
  - **Responsibilities:** Maintains a registry of available microservices and their instances.

- [x] **Config server:**
  - **Responsibilities:** Stores application configuration files and keeps track of their versions.

## Overall tech Stack:
- **Backend:** Java 17, 
               Spring Boot 3, 
               Spring Cloud (Config, Gateway, Netflix Eureka (service discovery and registration)), 
               Spring Data (Jpa, MongoDB, Redis), 
               Spring Web, 
               Spring WebFlux
- **Database:** PostgreSQL, MongoDB, Redis 
- **Testing:** JUnit 5, Mockito
- **Deployment:** Docker, Kubernetes