FROM maven:3.9.9-amazoncorretto-21-alpine AS builder

WORKDIR /app

COPY ./pom.xml ./pom.xml

# fetch all dependencies
RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn -B clean package spring-boot:repackage && tree

FROM amazoncorretto:21-alpine3.20

RUN mkdir -p /cloudacademy/app
WORKDIR /cloudacademy/app
COPY --from=builder /app/target/stocks-*.jar ./stocks-api.jar
RUN chown -R 1001:1001 /cloudacademy/app && chmod -R 755 /cloudacademy/app

USER 1001
EXPOSE 8080

CMD ["java", "-jar", "stocks-api.jar"]