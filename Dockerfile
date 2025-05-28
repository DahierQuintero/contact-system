# Etapa 1: Build
FROM gradle:8.5-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Etapa 2: Run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Instalar bash
RUN apk add --no-cache bash

COPY --from=build /app/build/libs/contacts-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]
