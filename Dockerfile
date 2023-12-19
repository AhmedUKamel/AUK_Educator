FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY *.jar executable.jar
ENTRYPOINT ["java","-jar","/executable.jar"]
EXPOSE 8080