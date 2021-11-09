# build image microservices-issues from Docker file

mvn clean install

docker build -f Dockerfile -t azizzakiryarov/microservices-issues:latest .

docker push azizzakiryarov/microservices-issues:latest

kubectl delete deployment.apps/microservices-issues-deployment service/microservices-issues

kubectl apply -f /Users/azizzakiryarov/IdeaProjects/microservices-issues/k8s/base/microservices-issues/deployment.yaml

kubectl apply -f /Users/azizzakiryarov/IdeaProjects/microservices-issues/k8s/base/microservices-issues/service.yaml