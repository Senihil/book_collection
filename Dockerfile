FROM openjdk:17-jdk-alpine3.14

COPY "./target/book_collection.jar" "/application/book_collection.jar"

CMD ["java", "-jar", "/application/book_collection.jar"]
