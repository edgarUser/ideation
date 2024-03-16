#!/bin/bash

while getopts a: flag
do
  case "$flag" in
    a) action=${OPTARG};;
    *) action="NA";;
  esac
done

if [ -z "${action}" ]; then
  echo "action argument is missing"
  exit
fi

if [ "${action}" = "on" ]; then
  echo "turn on minikube"
  minikube start --addons=metric-server --addons=dashboard  --addons="ingress" --addons="ingress-dns" #--cni=calico
  minikube status
  minikube dashboard
  #minikube node add
  #minikube image load ${local_image_name}
elif [ "${action}" = "off" ]; then
  echo "turn off minikube"
  minikube delete --all
else
  echo "Option no identified"
fi
