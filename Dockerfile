FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV server.port=8080
ENTRYPOINT ["java","-jar","app.jar"]