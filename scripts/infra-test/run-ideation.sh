#!/bin/bash

minikube image load org.ideation/rest-layer:2024.1.3
kubectl apply -f .
minikube service ideation-service --url