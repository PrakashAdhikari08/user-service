FROM openjdk:11 AS JAVA_VERSION
EXPOSE 80
ARG JAR_FILE=target/user-service.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]