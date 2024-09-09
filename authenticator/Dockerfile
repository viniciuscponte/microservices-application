FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/authenticator-0.0.1.jar /app/authenticator-0.0.1.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "authenticator-0.0.1.jar"]