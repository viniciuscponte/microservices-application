FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/consumer-0.0.1.jar /app/consumer-0.0.1.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "consumer-0.0.1.jar"]