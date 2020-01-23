FROM maven:3-jdk-8

COPY target/movielist.jar /app/

ENTRYPOINT ["java", "-jar", "/app/movielist.jar"]