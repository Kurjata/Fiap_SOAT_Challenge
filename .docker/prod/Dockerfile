FROM maven:3.9.9-eclipse-temurin-21-alpine as builder

WORKDIR /build

COPY ../../pom.xml .
COPY ../../src ./src

RUN mvn clean package -DskipTests --batch-mode

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=builder /build/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
