# SwiftLing App - User Service

## Overview
SwiftLing User Service is a microservice responsible for handling user-related operations in the SwiftLing application. It is built using **Spring Boot** and registered with **Netflix Eureka** for service discovery. It integrates with **Keycloak** for authentication, **Spring Cloud Config** for centralized configuration, and **Zipkin** for distributed tracing.

## Prerequisites
Ensure the following dependencies are installed and configured:
- **Java 21 or later**
- **Maven 3.9+** (compatible with Java 21)
- **Docker** (for running Zipkin and Keycloak via containers)
- **Docker Compose** (for running Keycloak if using standalone Docker installations)
- **Keycloak Server** (OAuth2 Provider) (Keycloak Docker Compose Example = https://github.com/CundullahT/swiftling-keycloak-docker-compose.git)
- **Netflix Eureka Server** (Service Discovery) (Example Eureka Server Repository = https://github.com/CundullahT/swiftling-discovery-service.git)
- **Spring Cloud Config Server** (Configuration Management) (Example Config Server Repository = https://github.com/CundullahT/swiftling-config-service.git)
- **Zipkin Server** (Distributed Tracing) **NOTE:** You can find the needed Docker command at the end of this document.
- **Google Account Application Password** (for sending out emails to the users)
- **Google Account Email Address** (for sending out emails to the users)

## Environment Variables
The following environment variables must be set for the application to function properly:

| Variable Name                 | Description                                                                                             |
|-------------------------------|---------------------------------------------------------------------------------------------------------|
| `KEYCLOAK_CLIENT_SECRET`      | The client secret for the configured Keycloak client (retrieve from Keycloak's client credentials tab). |
| `KEYCLOAK_SERVICE`            | Base URL of the Keycloak server (e.g., `http://localhost:8080`).                                        |
| `LOG_TRACE_SERVICE`           | Base URL of the Zipkin server (e.g., `http://localhost:9411`).                                          |
| `SWIFTLING_CONFIG_SERVICE`    | Base URL of the Spring Cloud Config Server (e.g., `http://localhost:8888`).                             |
| `SWIFTLING_DISCOVERY_SERVICE` | Base URL of the Eureka server (e.g., `http://localhost:8761`).                                          |
| `SWIFTLING_USER_DB_URL`       | JDBC URL for the User Service database (e.g., `jdbc:postgresql://localhost:5432/swiftling_user_db`).    |
| `SWIFTLING_USER_DB_USERNAME`  | Database username for the User Service database (set your own username).                                |
| `SWIFTLING_USER_DB_PASSWORD`  | Database password for the User Service database (set your own password).                                |
| `SWIFTLING_PROFILE`           | Active Spring profile (e.g., `local`, `dev`, `prod`).                                                   |
| `SWIFTLING_HOSTNAME`          | Hostname (if available, if none `demo` can be used).                                                    |
| `GMAIL_APP_PASSWORD`          | Application password of the google account that is used to send emails to the users.                    |
| `GMAIL_EMAIL`                 | The google account email address that is used to send emails to the users.                              |
| `MASTER_PASSWORD`             | The master/admin password used in Keycloak to be able to connect to master realm to create users.       |
| `MASTER_USER`                 | The master/admin username used in Keycloak to be able to connect to master realm to create users.       |

## Running the Application
1. Clone the repository:
   ```sh
   git clone <repository_url>
   cd <project_directory>
   ```
2. Set the required environment variables (for Linux/macOS):
   ```sh
   export KEYCLOAK_CLIENT_SECRET=your_client_secret
   export KEYCLOAK_SERVICE=your_keycloak_url
   export LOG_TRACE_SERVICE=your_zipkin_url
   export SWIFTLING_CONFIG_SERVICE=your_config_server_url
   export SWIFTLING_DISCOVERY_SERVICE=your_eureka_server_url
   export SWIFTLING_USER_DB_URL=your_database_jdbc_url
   export SWIFTLING_USER_DB_USERNAME=your_database_username
   export SWIFTLING_USER_DB_PASSWORD=your_database_password
   export SWIFTLING_PROFILE=dev
   export SWIFTLING_HOSTNAME=demo
   export GMAIL_APP_PASSWORD=your_gmail_application_password
   export GMAIL_EMAIL=your_gmail_email_address
   export MASTER_PASSWORD=your_keycloak_admin_master_password
   export MASTER_USER=your_keycloak_admin_master_username
   ```
   For Windows (Command Prompt):
   ```cmd
   set KEYCLOAK_CLIENT_SECRET=your_client_secret
   set KEYCLOAK_SERVICE=your_keycloak_url
   set LOG_TRACE_SERVICE=your_zipkin_url
   set SWIFTLING_CONFIG_SERVICE=your_config_server_url
   set SWIFTLING_DISCOVERY_SERVICE=your_eureka_server_url
   set SWIFTLING_USER_DB_URL=your_database_jdbc_url
   set SWIFTLING_USER_DB_PASSWORD=your_database_password
   set SWIFTLING_USER_DB_USERNAME=your_database_username
   set SWIFTLING_PROFILE=dev
   set SWIFTLING_HOSTNAME=demo
   set GMAIL_APP_PASSWORD=your_gmail_application_password
   set GMAIL_EMAIL=your_gmail_email_address
   set MASTER_PASSWORD=your_keycloak_admin_master_password
   set MASTER_USER=your_keycloak_admin_master_username
   ```
3. Build the project using Maven:
   ```sh
   mvn clean package
   ```
4. Run the User Service application:
   ```sh
   java -jar target/*.jar
   ```
   OR using Spring Boot:
   ```sh
   mvn spring-boot:run
   ```

## API Documentation (Swagger)
- **Swagger UI**: `http://localhost:7001/swagger-ui.html`
- **API Docs**: `http://localhost:7001/v3/api-docs`

**Note:** The API documentation is also accessible through the API Gateway, which consolidates all microservices' API docs.

## Default Configuration
- The User Service runs on **port 7001**.
- Uses **OAuth2 authentication via Keycloak**.
- Registers itself with Eureka for service discovery.
- Retrieves configuration properties from Spring Cloud Config Server.
- Integrates with Zipkin for distributed tracing.

## Additional Information
- This application is built using **Spring Boot 3.4.4**.
- Ensure that all dependent services (Keycloak, Eureka, Config Server, Zipkin) are running before starting the User Service.
- Logs and tracing information will be sent to Zipkin if configured correctly.
- To run Zipkin using Docker, execute the following command:
  ```sh
  docker run -d -p 9411:9411 openzipkin/zipkin
  ```
- For an example **Keycloak** setup using Docker Compose, refer to this private repository:
  https://github.com/CundullahT/swiftling-keycloak-docker-compose.git
- While the User Service does not directly depend on the API Gateway, starting the Gateway allows accessing this service through it, including using its Swagger documentation. The Gateway may take some time to retrieve and display the Swagger docs from microservices.

## License
This project is licensed under [MIT License](LICENSE).
