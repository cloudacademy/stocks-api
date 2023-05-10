FROM openjdk:17-bullseye AS builder

RUN apt-get update && \
    apt-get install -y build-essential maven tree

COPY src ./src
COPY pom.xml .
RUN mvn -B clean package spring-boot:repackage -DskipTests
#RUN tree

FROM amazoncorretto:17-alpine3.17

RUN mkdir -p /cloudacademy/app
WORKDIR /cloudacademy/app
RUN ls -la

COPY --from=builder target/stocks-*.jar ./stocks-api.jar

EXPOSE 8080

CMD ["java", "-jar", "stocks-api.jar"]