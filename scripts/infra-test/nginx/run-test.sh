#!/bin/bash

echo "Running nginx deployment and service"
kubectl apply -f .
kubectl get pods
minikube ip
kubectl get services
minikube service nginx-deployment-service --url