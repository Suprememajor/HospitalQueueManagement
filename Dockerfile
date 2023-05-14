FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /build
COPY src /build/src
COPY pom.xml /build

RUN mvn clean install -DskipTests

FROM openjdk:17-alpine AS deploy

COPY --from=build /build/target/management-service.jar /management-service/management-service.jar
WORKDIR /management-service
ENTRYPOINT ["java", "-jar", "management-service.jar"]
EXPOSE 8080