FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . /app
RUN mvn -pl services/notification-service -am -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/services/notification-service/target/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]
