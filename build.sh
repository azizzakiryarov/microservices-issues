# build image microservices-issues from Docker file

mvn clean install

docker build -f Dockerfile -t azizzakiryarov/microservices-issues:latest .

docker push azizzakiryarov/microservices-issues:latest
