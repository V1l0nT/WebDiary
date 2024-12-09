FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/WebDiary_UserService-1.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
