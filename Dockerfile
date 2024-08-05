# Stage 1: Build the application with Maven
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application with JDK
FROM openjdk:17
WORKDIR /app
COPY ./target/Scrapping-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app.jar"]
