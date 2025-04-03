# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/ecommerce-backend.jar /app/ecommerce-backend.jar

# Expose the port that Spring Boot app will run on
EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java", "-jar", "ecommerce-backend.jar"]
