### deploy shared memcache as K8 statefulset 

- replicated, sharded caches 

#### How to run 
- `kubectl create -f memcached-shards.yaml` 
- kubectl create -f memcached-service.yaml
- kubectl create configmap --from-file=nutcracker.yaml twem-config.
- kubectl create -f memcached-ambassador-pod.yaml


- deploy replicated shard router service 
- config map 
    - kubectl create configmap --from-file=shared-nutcracker.yaml shared-twem-config

- kubectl create -f shared-twemproxy-deploy.yaml
- kubectl create -f shard-router-service.yaml



#### Sharding function 
- Shard = hash(Req) % 10
- shard(country(request.ip), request.path)
- IP 

- select a key 


#### consistent HTTP sharding proxy 
- key = full req URI 



#### sharded, replicated serving 



#### Hot sharding system 
<a href="https://imgur.com/aRIcrXL"><img src="https://i.imgur.com/aRIcrXL.png" title="source: imgur.com" /></a>




#### resource 
- https://github.com/twitter/twemproxy
- Facebook distributed architecure 
