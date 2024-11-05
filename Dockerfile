FROM openjdk:17-jdk-alpine
EXPOSE 8082
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
ENTRYPOINT ["java", "-jar", "gestion-station-ski-1.0.jar"]
