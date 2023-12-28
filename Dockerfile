FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-oracle
COPY --from=build /home/app/target/*.jar /usr/local/lib/executable.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/executable.jar"]