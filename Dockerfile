FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY target/financial-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","financial-0.0.1-SNAPSHOT.jar"]
