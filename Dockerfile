FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/challenges-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "challenges-0.0.1-SNAPSHOT.jar"]