FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /usr/app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=builder /usr/app/target/swiftling-user-service-0.0.1-SNAPSHOT.jar /swiftling-user-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "swiftling-user-service-0.0.1-SNAPSHOT.jar"]
