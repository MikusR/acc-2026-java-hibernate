# M9-Spring Security Task based on my M8 Task

Spring Boot REST API secured with Spring Security.

## Running the application

```
./mvnw spring-boot:run
```

## Users

| Username | Password | Role  |
|----------|----------|-------|
| user     | user123  | USER  |
| admin    | admin123 | ADMIN |

## Endpoints

| Method | Path              | Access                |
|--------|-------------------|-----------------------|
| GET    | /api/books/**     | Any authenticated user|
| POST   | /api/books/borrow | Any authenticated user|
| POST   | /api/books/return | Any authenticated user|
| POST   | /api/books        | ADMIN only            |
| PUT    | /api/books/**     | ADMIN only            |
| DELETE | /api/books/**     | ADMIN only            |
