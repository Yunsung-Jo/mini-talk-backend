FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/*.jar mini-talk.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mini-talk.jar"]