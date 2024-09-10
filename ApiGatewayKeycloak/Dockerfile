# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/*.jar app.jar

# Expose the port that your microservice will run on
EXPOSE 9876

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
