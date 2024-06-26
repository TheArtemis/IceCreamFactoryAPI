FROM openjdk:17-alpine
LABEL authors="l.deflorian"

WORKDIR /app

COPY ./target/IceCreamFactoryAPI-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "IceCreamFactoryAPI-0.0.1-SNAPSHOT.jar"]
