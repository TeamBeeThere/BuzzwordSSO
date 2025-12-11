FROM sapmachine:21-jre-alpine

# WORKDIR /code

COPY target/sso-0.0.1-SNAPSHOT.jar app.jar

# EXPOSE 6767

ENTRYPOINT ["java","-jar","/app.jar"]