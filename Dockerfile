FROM openjdk:11-jre-slim

COPY target/*.jar /transactions/app.jar

ENTRYPOINT ["java", "-jar", "/transactions/app.jar"]
