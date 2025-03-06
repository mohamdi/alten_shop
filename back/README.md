# ALTEM Shop API

## Project dependencies
This project is using Java 21, Spring Boot 3.3.4.

## Project structure
The project structure is as follows:

```
.
├── README.md
├── build.gradle
├── settings.gradle
├── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── alten
    │   │           └── shop
    │   │               └── AltenShopApplication.java
    │   │                   └── config
    │   │                   └── controller
    │   │                   └── model
    │   │                       └── entity
    │   │                       └── dto
    │   │                       └── mapper
    │   │                       └── enumeration
    │   │                   └── service
    │   │                   └── repository
    │   │                
    │   └── resources
    │       └── db
    │           └── changelog
    │               └── db.changelog-master.xml
    │               └── init.sql
    │               └── init-data.sql
    │       └── application.properties
    │       └── application-local.properties
    └── test
        └── java
            └── com
                └── alten
                    └── shop
                        └── AltenShopApplicationTests.java
```

# Instructions to run this app
use the profile `local` to use postgresql database.
edit the file application-local.properties to configure the database connection.

You can find the postman collection in the root of the project.
