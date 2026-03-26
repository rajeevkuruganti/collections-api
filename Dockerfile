# Build stage
FROM gradle:8.0.2-jdk19 AS builder
WORKDIR /home/gradle/src

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew clean bootJar -x test


# Runtime stage (no circular reference)
FROM azul/zulu-openjdk:17-alpine
WORKDIR /app

COPY --from=builder /home/gradle/src/build/libs/collections-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]