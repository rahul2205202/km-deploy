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

# Stage 2: Create the final Docker image with MySQL
FROM openjdk:23-jdk

# Install MySQL
RUN apt-get update && apt-get install -y mysql-server

# Configure MySQL (Set root password)
RUN service mysql start && \
    mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root123';" && \
    mysql -e "CREATE DATABASE krushimarket;" && \
    service mysql stop

# Stage 2: Create the final Docker image using OpenJDK 23

FROM openjdk:23-jdk

VOLUME /tmp



# Copy the JAR from the build stage

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080
EXPOSE 3306

# Start MySQL and then the Spring Boot application
CMD service mysql start && java -jar app.jar
