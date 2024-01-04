# Maven Build Stage
FROM maven:latest AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Java Execution Stage
FROM openjdk:17-oracle
WORKDIR /usr/local/lib
COPY --from=build /home/app/target/*.jar /usr/local/lib/executable.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/executable.jar"]