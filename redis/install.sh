kubectl create -f redis-shards.yaml



kubectl create -f redis-service.yaml



kubectl create configmap twem-config --from-file=./nutcracker.yaml
