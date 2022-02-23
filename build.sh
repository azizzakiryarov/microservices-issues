# build image microservices-issues from Docker file

mvn clean install

docker build -f Dockerfile -t azizzakiryarov/microservices-issues:latest .

docker push azizzakiryarov/microservices-issues:latest

kubectl delete statefulset.apps/microservices-issues-statefulset service/microservices-issues

kubectl apply -f /Users/azizzakiryarov/IdeaProjects/microservices-issues/k8s/base/microservices-issues/statefulset.yaml

kubectl apply -f /Users/azizzakiryarov/IdeaProjects/microservices-issues/k8s/base/microservices-issues/service.yaml