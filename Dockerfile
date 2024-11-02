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
ENV DATABASE_URL="jdbc:postgresql://db:5432/testdb"
RUN ./mvnw package -DskipTests

# Stage 2: Use a lighter image to run the application
FROM eclipse-temurin:11-jre-alpine AS final

ARG APP_NAME
ENV APP_PATH="/app/${APP_NAME}.jar"

# Copy the native executable from the builder stage
COPY --from=build-image /app/target/${APP_NAME}.jar ${APP_PATH}

# Expose port 8080
EXPOSE 8080

# Run the native executable
ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", "-XX:+UseContainerSupport", "-jar", "${APP_PATH}"]
