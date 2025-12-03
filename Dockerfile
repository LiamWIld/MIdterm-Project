FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/pipeline-demo-1.0.jar app.jar
EXPOSE 8080
CMD ["java","-cp","app.jar","com.example.App"]
