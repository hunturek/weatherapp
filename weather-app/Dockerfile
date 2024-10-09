FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/weather-app.jar .

ENTRYPOINT ["java", "-jar", "weather-app.jar"]
