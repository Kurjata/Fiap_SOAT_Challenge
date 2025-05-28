FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /app

# Instalar ferramentas básicas de desenvolvimento
RUN apk add --no-cache curl jq bash

# Copiar apenas o pom.xml primeiro para aproveitar o cache das dependências
COPY ../../pom.xml .
RUN mvn dependency:go-offline

# Copiar o código fonte
COPY ../../src ./src

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Configurar volume para hot-reload
VOLUME /app/src

# Comando para executar a aplicação em modo de desenvolvimento
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'"]