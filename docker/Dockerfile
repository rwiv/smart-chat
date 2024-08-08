# spring build
FROM gradle:8.7.0-jdk21-alpine as grapdle
WORKDIR /app/server
COPY . .
RUN gradle :modules:app:app-api:build -x check --parallel

# main
FROM openjdk:23-slim
WORKDIR /app
COPY --from=grapdle /app/server/modules/app/app-api/build/libs/app-api-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
