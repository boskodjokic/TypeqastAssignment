FROM openjdk:11
ADD target/typeqast-assignment-0.0.1-SNAPSHOT.jar docker-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-app.jar"]