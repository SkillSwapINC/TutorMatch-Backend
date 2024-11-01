# TutorMatch Platform

## Descripción
TutorMatch es una plataforma diseñada para conectar tutores con estudiantes. Esta aplicación de backend está construida utilizando Spring Boot y Maven.

## Requisitos Previos
- Java 22 o superior
- Maven 3.6.0 o superior
- MySQL 5.7 o superior

## Configuración del Proyecto

### Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/tutormatch.git
cd tutormatch
```

### Configurar la Base de Datos
1. Crear una base de datos MySQL llamada `tutormatch`
    
    ```bash
    mysql -u root -p
    CREATE DATABASE tutormatch;
    ```

2. Configurar el usuario y contraseña de MySQL en `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tutormatch
spring.datasource.username=tumysqlusuario
spring.datasource.password=tumysqlcontraseña
```

### Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

### Construir el Proyecto
```bash
mvn clean install
```

### Ejecutar la Aplicación
```bash
mvn spring-boot:run
```

## Endpoints de la API
La API de TutorMatch tiene los siguientes endpoints:

* POST /tutoring - Crear una tutoría
* GET /api/v1/users - Obtener una lista de todos los usuarios
* POST /api/v1/users - Crear un nuevo usuario
* GET /api/v1/users/role/{role} - Obtener una lista de usuarios por su rol
* GET /api/v1/users/email/{email} - Obtener un usuario por su email
