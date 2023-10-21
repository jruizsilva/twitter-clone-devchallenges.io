FROM eclipse-temurin:17.0.8.1_1-jre-alpine AS build
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/twitter-clon-0.0.1-SNAPSHOT.jar /app/twitter-clon-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/twitter-clon-0.0.1-SNAPSHOT.jar"]