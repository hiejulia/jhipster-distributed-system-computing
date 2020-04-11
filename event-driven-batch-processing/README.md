## Event driven batch processing 
- link work queue 
- workflow : directed, acyclic graph 

### paterns of event driven processing
- copier 
     - render a video - format - 4K high resolution format for playing off of a hard drive
     - 1080 pixel rendering of digital streaming 
     - low resolution ormat for streaming to mobile users on slow network
     - animated GIF thumnail for display in a movie picking UI 
- filter 
- splitter 
- sharder 
    - sharding algorithms 
- merger 


### pub-sub 


### deployment 
- deploy kafka
    - container using k8 cluster & helm package manager 
    - `helm init`
    - `helm repo add incubator http://storage.googleapis.com/kubernetes-charts-incubator
helm install --name kafka-service incubator/kafka`

- 3 `users` topic : `User-1`,`User-2`,`User-3`

