# Importing JDK and copying required files
FROM openjdk:23-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn

# Set execution permission for the Maven wrapper
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 23
FROM openjdk:23-jdk
VOLUME /tmp

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

ENV SPRING_DATASOURCE_URL jdbc:mysql://krushimarket.cvecmisumk5f.eu-north-1.rds.amazonaws.com:3306/your-database-name
ENV SPRING_DATASOURCE_USERNAME admin
ENV SPRING_DATASOURCE_PASSWORD root123
