# Project: Task Management API

## Features
- **User Authentication**:
  - **Description:** Users should be able to sign up, log in, and manage their profiles.
    - **Methods:** *Checked if implemented*
      - [ ] OAuth 2.0

- **Task CRUD Operations:** *Checked if implemented*
  - [ ] Allow users to create tasks with details like title, description, due date, priority, and status (e.g., to-do, in-progress, completed).
  - [ ] Implement endpoints to update task details.
          Allow users to delete tasks.
          Fetch tasks based on various criteria like status, due date, priority, etc.

- **Task Categories/Projects:** *Checked if implemented*
  - [ ] Enable users to organize tasks into categories or projects. Each category/project can have its own set of tasks.
  - [ ] Implement endpoints to create, update, and delete categories/projects.

- **Task Comments:** *Checked if implemented*
  - [ ] Allow users to add comments to tasks. Implement a sub-document structure in MongoDB to store comments within the task documents.

- **Task Reminders:** *Checked if implemented*
  - [ ] Implement a feature that sends email reminders to users for tasks that are nearing their due date.

- **Task History/Logs:** *Checked if implemented*
  - [ ] Track changes to tasks, such as status updates or edits, and store this history. Implement a change log feature that users can view to see the history of their tasks.

- **Reports/Analytics:** *Checked if implemented* 
  - [ ] Provide basic analytics, such as the number of tasks completed over time or tasks grouped by priority or status.
                      Generate reports on user productivity, showing how many tasks were completed on time, overdue, etc.

## Tech Stack:
- **Backend:** Java 17, Spring Boot 3
- **Database:** MongoDB 
- **Testing:** JUnit 5, Mockito