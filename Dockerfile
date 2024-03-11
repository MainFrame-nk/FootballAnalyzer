FROM openjdk:17-oracle
ARG JAR_FILE=target/FootballAnalyzer-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
LABEL authors="MainFrame"
ENTRYPOINT ["java", "-jar", "app.jar"]