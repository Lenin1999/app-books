#FROM openjdk:17-jdk-slim
#
#ARG NAME_APP=app-books
#ARG VERSION_APP=1.0-SNAPSHOT
#COPY ./build/install/$NAME_APP/lib/$NAME_APP-$VERSION_APP.jar ./
#COPY ./build/install/$NAME_APP/lib ./lib
#ENV DB_URL=jdbc:postgresql://db:5432/distribuida1
#ENV DB_USERNAME=postgres
#ENV DB_PASSWORD=12345
FROM eclipse-temurin:17.0.5_8-jre-alpine

RUN mkdir /app
WORKDIR /app

COPY build/libs/*.jar app.jar
COPY build/libs/libs ./libs


#CMD ["java", "-cp", "$NAME_APP-$VERSION_APP.jar:./lib/*", "run.Main"]
CMD ["java", "-jar", "app.jar"]
#EXPOSE 8080