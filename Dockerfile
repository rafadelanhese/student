FROM amazoncorretto:17.0.11-al2023-headless
VOLUME /tmp
COPY build/libs/student-0.0.1-SNAPSHOT.jar app.jar
CMD ["sh","-c","java -jar app.jar"]

java -jar student-0.0.1-SNAPSHOT.jar