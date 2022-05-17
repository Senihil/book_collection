FROM openjdk:17-jdk-alpine3.14

COPY "./target/bookcollection.jar" "/application/bookcollection.jar"

CMD ["java", "-jar", "/application/bookcollection.jar"]
