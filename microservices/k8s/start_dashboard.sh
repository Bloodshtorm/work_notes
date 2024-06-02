#!/bin/bash

# Запускаем Minikube Dashboard
minikube dashboard --port=43000 &

# Запускаем kubectl proxy с указанием адреса и порта
kubectl proxy --address='0.0.0.0' --disable-filter=true --port=9001
