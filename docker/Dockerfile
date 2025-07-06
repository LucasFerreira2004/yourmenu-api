# Parte 1: build
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /build

COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Parte 2: runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

CMD ["java","-jar","/app/app.jar"]
