FROM openjdk:11
EXPOSE 8085
COPY  target/User-Service-0.0.1-SNAPSHOT.jar User-Service-0.0.1-SNAPSHOT.jar
CMD java -jar User-Service-0.0.1-SNAPSHOT.jar
