FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
COPY javadoc /javadoc
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]