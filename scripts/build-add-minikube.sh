#!/bin/bash

if [ "$(minikube status | grep -ic Stopped)" -gt 1 ]; then
  echo "Minikube down"
  sh scripts/infra/minikube.sh -a on
else
  echo "Minikube up"
fi
./gradlew jibDockerBuild
minikube image load org.ideation/rest-layer:0.0.1-SNAPSHOT
