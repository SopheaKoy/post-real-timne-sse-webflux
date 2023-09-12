FROM openjdk:17
ARG JAR_FILE=build/libs/post-real-time-sse-web-flux-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} post-real-time-sse-web-flux-0.0.1-SNAPSHOT.jar
EXPOSE 6060
ENTRYPOINT ["java", "-jar" , "post-real-time-sse-web-flux-0.0.1-SNAPSHOT.jar"]