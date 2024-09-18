
# User JSON API

This is a REST API project that consumes an external API for managing user information using Spring Boot, WebClient, and caching strategies. The project follows SOLID principles and includes global exception handling, caching, and Swagger for API documentation.

## Requirements

- Java 17 or higher
- Gradle 7.x or higher
- Internet connection to download dependencies

## Setup Instructions

### 1. Clone the repository

```
git clone <repository-url>
cd user-json-api
```

### 2. Build the project

You can build the project using Gradle by running the following command:

```
./gradlew build
```

### 3. Running the application

You can run the application with the following Gradle command:

```
./gradlew bootRun
```

The application will start on the default port `8080`. If you need to change the port, you can modify the `application.properties` file.

### 4. Swagger Documentation

Once the application is running, you can access the Swagger UI for API documentation by visiting:

```
http://localhost:8080/v3/api-docs
```

### 5. Testing

The application includes unit tests that can be run using the following command:

```
./gradlew test
```

## Caching

This project uses caching to reduce the number of requests made to the external API. Cache entries expire after 5 minutes and are refreshed automatically. No additional configuration is needed, as the cache is enabled by default.

## Configuration

- You can configure the external API base URL, cache settings, and other properties in the `application.properties` file located in the `src/main/resources` directory.
- Ensure to replace the `<API_BASE_URL>` with the actual base URL of the external API you are using.

## Dependencies

- Spring Boot
- WebFlux
- Spring Caching (Caffeine)
- SpringDoc OpenAPI (Swagger)
- Lombok
- JUnit 5 & Mockito for testing
