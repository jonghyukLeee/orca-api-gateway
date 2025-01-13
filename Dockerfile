FROM openjdk:17

WORKDIR /app

ARG DEPLOY_EUREKA
CMD ["echo", "$DEPLOY_EUREKA"]
ENV DEPLOY_EUREKA=${DEPLOY_EUREKA}

COPY build/libs/*.jar /app/app.jar
COPY src/main/resources/ /app/resources/

CMD ["java", "-jar", "./app.jar", "--spring.config.location=file:./resources/"]