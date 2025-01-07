# Hunter's League Backend API

## Overview

The **Hunter's League** backend is a robust system designed to modernize the management of hunting competitions for a club located in Québec. With over **120 million rows** in the database, the system leverages advanced backend technologies to ensure high performance, scalability, and reliability.

### Key Features
 
- **Member Management:** Online registration, secure login, and personal profile management.
- **Competition Management:** Organize, register, and manage weekly competitions.
- **Species Database:** View, add, update, or delete species (fish, big game, birds) with their characteristics.
- **Leaderboard & Results:** Automated scoring and real-time rankings.
- **Historical Insights:** Members can view their competition history and performance trends.
- **High Data Volume Management:** Efficient handling of massive datasets with advanced querying techniques.


---

## Tech Stack

- **Spring Boot**: Backend framework for creating RESTful APIs.
- **Spring Security**: Ensures robust authentication and role-based authorization.
- **Database**: Managed in Docker containers for scalability and consistency.
- **SonarQube**: Integrated for continuous quality and security analysis.
- **Liquibase**: Ensures database version control and schema management.
- **Lombok + Builder Pattern**: Simplifies boilerplate code.
- **MapStruct**: Handles object mapping efficiently.
- **JWT Authentication**: Ensures secure access control.
- **Bcrypt**: Secures password storage.

---

## Architecture

The application follows a layered architecture:

1. **Controller Layer**: Handles HTTP requests.
2. **Service Layer**: Contains business logic.
3. **Repository Layer**: Communicates with the database.

### Data Flow
1. **User Request** → **Controller** → **Service** → **Repository** → **Database**
2. **Response** follows the reverse path.

---

## Security: Roles and Permissions

### Project Context

In the Hunter's League project, ensuring robust security is essential to protect user data, rankings, and sensitive competition information. The solution guarantees reliable authentication and precise authorization control, considering the specific roles within the application.

### Core Security Features

- Development of a primary class named `USER`, responsible for managing users and their authorizations.

### Application Roles and Permissions

The available roles in the application are:

#### MEMBER

Standard user participating in competitions.
- **CAN_PARTICIPATE**: Register for a competition.
- **CAN_VIEW_RANKINGS**: View rankings.
- **CAN_VIEW_COMPETITIONS**: View competition details.

#### JURY

Users with specific responsibilities related to competition management and evaluation.
- All permissions of the MEMBER role.
- **CAN_SCORE**: Assign scores to participants.

#### ADMIN

Supervisors with elevated privileges to manage the entire application.
- All permissions of the MEMBER and JURY roles.
- **CAN_MANAGE_COMPETITIONS**: Add or modify competition details.
- **CAN_MANAGE_USERS**: Manage user accounts.
- **CAN_MANAGE_SPECIES**: Add, update, or delete species information.
- **CAN_MANAGE_SETTINGS**: Configure application settings.

---

## API Endpoints

### Users
- `POST /api/auth/register`: Register a new user.
- `POST /api/auth/login`: Authenticate and obtain a JWT token.
- `GET /api/users/findAll`: Retrieve all users (paginated).
- `GET /api/users/search`: Search users by username or ID.

### Competitions
- `POST /api/competitions/create`: Create a new competition.
- `POST /api/competitions/register/{competitionId}`: Register a user for a competition.
- `GET /api/competitions/{competitionId}/rankings`: Get competition rankings.

### Species
- `POST /api/species/create`: Add a new species.
- `PUT /api/species/update/{id}`: Update species details.
- `DELETE /api/species/delete/{id}`: Remove a species.
- `GET /api/species/findAll`: List all species.

### Scoring Formula
Score = Points_Associated + (Weight × Weight_Factor) × Difficulty_Factor

---

## Quality and Performance

### SonarQube Integration
The project is continuously analyzed by **SonarQube**, ensuring:
- Code quality metrics.
- Identification of bugs and vulnerabilities.
- Enforcement of coding standards.

### Database Management
Using **Docker**, the database runs in an isolated and reproducible environment, ensuring:
- Easy deployment and scaling.
- Consistent development and production setups.

### Handling 120M Rows

Efficient database management is critical for a system with such a large dataset. The following strategies are implemented to optimize performance and scalability:

- **Indexing**: Critical database fields, such as user IDs, competition codes, and species identifiers, are indexed to ensure fast lookups and query execution.
- **Optimized JPA Queries**: Bulk data operations are handled using carefully crafted queries to minimize overhead and maximize performance.
- **Materialized Views**: Precomputed results for complex queries, such as rankings and historical data, are stored as materialized views. This reduces the computational burden during real-time requests.
- **Stored Procedures with Batch Processing**: High-volume data operations, such as batch scoring and competition result updates, are managed through stored procedures. These procedures process data in batches, reducing lock contention and enhancing transaction throughput.
- **Partitioning**: The database is partitioned by logical units, such as competition weeks or species categories, to optimize read and write operations.

These strategies collectively ensure that the backend remains performant and reliable, even when handling datasets exceeding **120 million rows**.

---

## Constraints and Rules

- Password complexity: Minimum 8 characters, with uppercase, lowercase, and numeric characters.
- Competitions:
    - Weekly limit: One competition per week.
    - Registration closes 24 hours before the start.
    - Participants must have valid licenses.
- Auditing:
    - Changes to species are logged for traceability.

---

## Setup and Deployment

### Prerequisites
- **Java 17**
- **Maven**
- **Docker**
- **SonarQube**

### Steps to Run
1. Clone the repository.
2. Set up the database using Docker:
   ```bash
   docker-compose up -d
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access API via http://localhost:8080

---

## Testing

- **Postman Collection**: Use the provided Postman collection for testing API endpoints.
- **Unit Tests**: Execute all tests with:
   ```bash
   mvn test
   ```

---

#### Feel free to contribute or report issues to make Hunter's League even better! 🚀
