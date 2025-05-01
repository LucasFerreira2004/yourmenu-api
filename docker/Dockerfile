FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /build

COPY .env .env
COPY . .

RUN mvn -e clean install -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copia o .env da build
COPY --from=builder /build/.env .env

# Copia o jar
COPY --from=builder /build/target/*.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
