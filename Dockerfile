FROM openjdk:11-jdk-slim

ADD target/issues-service-0.0.1-SNAPSHOT.jar issues-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/issues-service-0.0.1-SNAPSHOT.jar"]

EXPOSE 8869