FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /build

COPY ../../pom.xml .
COPY ../../src ./src

RUN mvn clean package -DskipTests --batch-mode

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/build/target/soat-1.0.0.jar"]
