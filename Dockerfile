# =============================
# Stage 1 - Build the Image
# =============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

# Build the application (creates jar file)
RUN mvn clean package -DskipTests


# =============================
# Stage 2 - Run the Container
# =============================
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy only the built jar file from Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the container
ENTRYPOINT ["java", "-jar", "app.jar"]

