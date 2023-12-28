FROM maven:latest AS builder
WORKDIR /src/main/java/com/ahmedukamel/educator
COPY . .
RUN mvn clean compile package
FROM openjdk:17-oracle
WORKDIR /src/main/java/com/ahmedukamel/educator
COPY --from=builder /target/*.jar ./executable.jar
CMD ["java", "-jar", "executable.jar"]
EXPOSE 8080