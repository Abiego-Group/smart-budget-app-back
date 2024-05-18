FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY /build/libs/smart-budget.jar build/

WORKDIR /app/build
EXPOSE 8080
ENTRYPOINT java -jar smart-budget.jar
