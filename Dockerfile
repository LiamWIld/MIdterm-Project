FROM eclipse-temurin:17-jre
WORKDIR /app
ENV APP_NAME=final-project
COPY target/pipeline-demo-1.0.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
