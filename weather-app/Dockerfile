FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

RUN mvn clean package && ls -la target

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/weather-app-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "weather-app-1.0-SNAPSHOT.jar"]
