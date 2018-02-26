FROM openjdk:8-jdk-alpine
ADD . /ReportingService
WORKDIR /ReportingService
CMD ["java", "-jar", "target/hydra-reporting-service-0.0.1-SNAPSHOT.jar"]