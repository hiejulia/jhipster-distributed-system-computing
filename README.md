# jhipster-distributed-system-computing
- Distributed system that can serve high load
- scale system 
- HA
- Handle failure 
- External distributed storage system for recovery 


## Description
+ Netflix clone 
    + Live Video streaming service 
        + Architecture : Open Connect, CDN 
        + Transcode/ Encode service 
            + Validate service -> Media pipeline -> put chunk data into pipeline for parallel 
                + Archer : MapReduce platfor for media processing that use containers 
                    + Prores : 
                        + Detect dead pixels caused by defective digital camera 
                        + ML to tag audio
                        + QC for subtitles 

        + Search service : 
        + Datastorage : Hadoop 
        + 
    + Live stream movie features (with only friends who also has netflix account)
        + Streaming video content 
    + Social network 
        + Graph database 
    
    + Billing service
      + coupon service, invoice service, order service, payment service      
    + Metrics service + Logging service     
        + Kafka : distributed system monitoring 
            + Move data from kafka to sinks : ElasticSearch, S3 
        + Elasticsearch : set up 150 clusters - 3,500 instances hosting - 1.3 PB data 
        + Apache Chukwa : data collection system for monitor large distributed system - built on top of HDFS and Map/Reduce 
        + Time series database with Cassandra
    + Authentication service 
        + KSQL streaming, Schema registry, Avro, Kafka, Java producer, C# consumer
        + Credit card registry & email registry function
        + Healthcheck stream producer & consumer service 
    + Architecture :
        + AWS ELB : route traffic to front end service 
        + EVCache: sharded multiple copies of cache is sotred in shared nodes 

        + Data 
            + Move 1 TB data from RAM to SSD 
            + DB : EC2 deployed MySQL : master-master - Sync replication protocol 
            + Cassandra : 500 nodes - 50 cluster 
            + 
        + Container scale : AWS Titus : 
        + Reactive - Akka 
        + Spring cloud :
            + distributed messaging : Cloud bus link the nodes of a distributed system with a lightweigh message broker 
            + 
    + gRPC for 1 service : written with Go
+ Distributed crawler 


## Principle for performance tuning 
- Understand you env 
- TANSTAAFL!
- throughput versus latency 
- DO NOT OVERUTILIZE A RESOURCE



## Implementation 
- deployment service with kubernetes 
+ Distributed Cache server 
    + HazelCast distributed caching 
    - varnish distributed cache 
- replicated load balance 
- nginx replicated 
- sharded caching with memcache(replica)
    - twemproxy for Redis 

+ Distributed messaging 
    + ActiveMQ
    + Kafka 
+ Distributed DB
    + Data partition 
    + Riak 
    + Cassandra  
+ Distributed file system : 
    + Hadoop, HDFS
+ Distributed DNS 
+ Distributed proxy server
+ Distributed web server 
+ Utilize cloud services: 
    + AWS : AWS ELB 
    - Google S2 geometry lib 
    - cloud native d d
+ Network communication 
    + Async 
    + Axon framework : CQRS 
    + Web socket 
    + RMI, CORBA
    + gRPC 
    - TChannel : network multiplexing and framing protocol for RPC 
    
- distributed locking 
- distributed tracing, tracking, logging
- distributed scheduling 
- distributed security 
- distributed messaging, queuing, event streaming 
- distributed search 
- distributed storage 
- CD/CI scaling 



+ Distributed architecture  
    + peer-to-peer;

	+ client/server;
		- multi-tier;

	+ mobile agents;

    + Reactive 
        + Akka : HTTP, stream, clustering, sharding, actors 
        + Domain sourcing 
        + Distributed domain driven design 
        + CQRS 
    + Bulkhead pattern
    + Distributed domain  
- event sourcing architecture 
    - event driven batch processing 
        - distributed work queue 

- thrift 

## Availability 
- Resilience engineer 
- Failover
- LB
- Rate limit 
- Autoscale
- Global availability 
- HA 
- Circuit breaker
- timeouts


## Performance 
- OS, storage, database, network 
- Performance tuning with GC 
- Performance optimization with Image, video, page load 
- 

# Distributed cloud computing 


# Scalability 
+ Universal scalability laws 
    

# Server clustering 


# LB 

# Testing 
+ Multi JVM testing 

# Container distributed application 
+ Debug a service running in a container 
+ minimized docker images using multi stage
+ secure distributed app 
    + kubernetes secret 
    + secrets in env
    + External secrets like HashiCorp Vault 
+ make service scale  
+ techniques to increase resiliency 
+ availability check 
+ enable zero downtime updates 
+ Prod deployment 
    + Kubernetes pods, replicasets, deployment, services 
    + create template
    + orchestrator 
    + deploy on premise/ cloud 
    + peek into 2 big corp hosted kubernetes SaaS : Microsoft azure & google cloud 

+ Prod 
    + self heal 
    + update service, avoid cascading failures 
    





## Tech stack 
- C, C++ 
- Java, Spring, Spring cloud 
- Node.js, io.js
- Python 
- Go lang
- microservices
- cache
- kubernetes
- KUDA
- Data pipeline 
- cloud 
- Redis 
+ Active MQ 
+ Hazelcast 
+ Docker, Kubernetes on distributed system
+ Architecture
    + Reactive architecture: Java (Axon framework), Scala (Akka)
    + Event sourcing architecture 
+ Database in distributed system 



## Reference to 
+ Book 
- https://github.com/binhnguyennus/awesome-scalability 
+ Research paper (Graduate, PhD level )
+ Distributed system, large scale system : Uber, Netflix, Grab, AirBnB, Amazon, AWS, Google, Microsoft, Facebook, Apple 
+ Resource 
    + https://eng.uber.com/ureplicator-apache-kafka-replicator/
    + https://cwiki.apache.org/confluence/display/ZOOKEEPER/PoweredBy

- Facebook scalability / distributed system paper
    - Scaling backend authentication at facebook 
    - facebook distributed architecture : https://www.researchgate.net/publication/262689075_Overview_of_Facebook_scalable_architecture
    - Inside the Social Network data center Facebook


- Uber distributed system / scalability
    - http://highscalability.com/blog/2015/9/14/how-uber-scales-their-real-time-market-platform.html


- Netflix distributed system / scalability



- Google distributed system / scalability
    - Designing distributed system : Google case study 
        - web search 
            - deep search 
            - index, inverted index 
            - ranking - Page Rank 

        - massively multiplayer online games
        - financial trading 

    - Developing real world case studies 
    - Large scale cluster management at Google with Borg 
    - Google’s Data Architecture and What it Takes to Work at Scale
    


- AirBnB distributed system / scalability


- Microsoft distributed system / scalability


- Amazon distributed system / scalability
