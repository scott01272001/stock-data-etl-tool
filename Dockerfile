#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean package -Pprod -Dmaven.test.skip

#
# Package stage
#
FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/stock-data-etl-tool-0.0.1-SNAPSHOT.jar /usr/local/lib/stock-data-etl-tool-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","usr/local/lib/stock-data-etl-tool-0.0.1-SNAPSHOT.jar"]
