FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/WebDiary_DiaryService-1.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
