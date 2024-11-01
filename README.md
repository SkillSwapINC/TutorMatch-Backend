# TutorMatch Platform

## Description
TutorMatch is a platform designed to connect tutors with students. This backend application is built using Spring Boot and Maven.

## Prerequisites
- Java 22 or higher
- Maven 3.6.0 or higher
- MySQL 5.7 or higher

## Project Setup

### Clone the Repository
```bash
git clone https://github.com/tu-usuario/tutormatch.git
cd tutormatch
```

### Configure the Database
1. Create a MySQL database named `tutormatch`
    
    ```bash
    mysql -u root -p
    CREATE DATABASE tutormatch;
    ```

2. Set the MySQL username and password in `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tutormatch
spring.datasource.username=tumysqlusuario
spring.datasource.password=tumysqlcontraseña
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

## API Endpoints
The TutorMatch API has the following endpoints:

* POST /tutoring - Create a tutoring session
* GET /api/v1/users - Get a list of all users
* POST /api/v1/users - Create a new user
* GET /api/v1/users/role/{role} - Get a list of users by their role
* GET /api/v1/users/email/{email} - Get a user by their email
