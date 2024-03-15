#!/bin/bash

kubectl get pods
#kubectl get pod ${pod_name} -o yaml #get full information about a pod
kubectl describe pod nginx #get pod/deployment details based on pod name or tag
kubectl explain pod
kubectl explain pod.spec
kubectl explain pod.spec.restartPolicy
#kubectl port-fordward ${pod_name} 8080:8080 #Used to forward pod to validate if it is working
#kubectl logs ${pod_name} #use to review logs generated by pod
#kubectl logs -l app=${label-defined} #get logs based on label selector
#kubectl exec -ti ${pod_name} /bin/bash #use to go inside pod container
kubectl top pods