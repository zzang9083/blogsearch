FROM openjdk:11-jdk
ARG JAR_FILE=blogsearchservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} blog_api.jar
EXPOSE 8080
ENTRYPOINT  ["java", "-Dspring.profiles.active=docker", "-jar", "/blog_api.jar"]
