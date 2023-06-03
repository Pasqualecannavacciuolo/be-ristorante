FROM openjdk:17
ADD target/be-ristorante.jar be-ristorante.jar
ENTRYPOINT ["java", "-jar","be-ristorante.jar"]
EXPOSE 8080