FROM eclipse-temurin:19-jre-focal
MAINTAINER GreyCompany

ADD ./target/FitnessStudioApp.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/FitnessStudioApp.jar"]

EXPOSE 8080 8080