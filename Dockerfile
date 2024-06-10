FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
COPY docs/javadoc /javadoc
EXPOSE 8085
ENTRYPOINT ["java","-Djavadoc.path=/javadoc/","-jar","/app.jar"]