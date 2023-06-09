mvn install -DskipTests
docker build -t be-ristorante.jar .
docker-compose up -d