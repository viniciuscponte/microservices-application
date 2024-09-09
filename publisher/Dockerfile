FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/publisher-0.0.1.jar /app/publisher-0.0.1.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "publisher-0.0.1.jar"]