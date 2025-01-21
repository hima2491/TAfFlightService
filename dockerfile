FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/my-flightService-app.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]