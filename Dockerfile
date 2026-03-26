FROM azul/zulu-openjdk:17-latest
#VOLUME /tmp
#COPY --from=build/libs/ *.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
FROM gradle:8.0.2-jdk19 AS build
WORKDIR /home/gradle/src
COPY build.gradle settings.gradle gradlew ./
 # plus gradle/ if you use wrapper
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew clean bootJar -x test

WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/collections-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]