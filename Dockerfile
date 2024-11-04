# Stage 1: Build the application
FROM openjdk:11-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files and pom.xml for dependency installation
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .

# Download and cache dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the source code
COPY src ./src

ENV MAVEN_OPTS="-Dskip.docker.build=true"

RUN ./mvnw package -DskipTests

# Stage 2: Use a lighter image to run the application
FROM eclipse-temurin:11-jre-alpine AS final

ARG APP_NAME

# Copy the native executable from the builder stage
COPY --from=build-image /app/target/${APP_NAME}.jar /app/app.jar

# Expose port 8080
EXPOSE 8080

# Run the native executable
ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", "-XX:+UseContainerSupport", "-jar", "/app/app.jar"]
