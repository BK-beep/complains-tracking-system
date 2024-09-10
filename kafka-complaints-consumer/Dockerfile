# Build stage
FROM maven:3.9.9 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Install networking tools
RUN apt-get update && apt-get install -y curl iputils-ping dnsutils

EXPOSE 60661
ENTRYPOINT ["java", "-jar", "app.jar"]