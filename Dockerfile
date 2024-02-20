FROM amazoncorretto:17.0.7
COPY target/account-info-1.0.0-SNAPSHOT.jar account-info.jar
EXPOSE 8080 8778 9779
ENTRYPOINT ["java","-jar","/account-info.jar"]
