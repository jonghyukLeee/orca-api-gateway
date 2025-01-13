FROM openjdk:17

WORKDIR /app

ARG EUREKA
ENV EUREKA=$EUREKA

COPY build/libs/*.jar /app/app.jar
COPY src/main/resources/ /app/resources/

CMD ["java", "-jar", "./app.jar", "--spring.config.location=file:./resources/"]