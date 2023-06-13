FROM openjdk:20-jdk-slim
WORKDIR /app
COPY ./target/todo-app-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "todo-app-0.0.1-SNAPSHOT.jar"]