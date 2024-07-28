FROM openjdk:17
ARG JAR_FILE=target/phone-booking-0.0.1-SNAPSHOT.jar
COPY ./target/phone-booking-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]