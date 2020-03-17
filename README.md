# jhipster-distributed-system-computing
Distributed system that can serve high load
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
    + Billing service
      + coupon service, invoice service, order service, payment service      
    + Metrics service     
        + Kafka : distributed system monitoring 
            + Move data from kafka to sinks : ElasticSearch, S3 
        + Elasticsearch : set up 150 clusters - 3,500 instances hosting - 1.3 PB data 
        + Apache Chukwa : data collection system for monitor large distributed system - built on top of HDFS and Map/Reduce 
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
    
## Implementation 
+ Distributed Cache server 
    + HazelCast distributed caching 
+ Distributed messaging 
    + ActiveMQ
    + Kafka 
+ Distributed DB
    + Data partition 
    + 
    + Cassandra  
+ Distributed file system : 
    + Hadoop, HDFS
+ Distributed DNS 
+ Distributed proxy server
+ Distributed web server 
+ Utilize cloud services: 
    + AWS : AWS ELB 
+ Network communication 
    + Async 
    + Axon framework : CQRS 
    + Web socket 
    + RMI, CORBA
    + gRPC 

+ Distributed architecture  
    + peer-to-peer;

	+ client/server;
		- multi-tier;

	+ mobile agents;


## Tech stack 
+ Java, Spring, Spring cloud 

+ 





## Reference to 
+ Book 
+ Research paper (Graduate, PhD level )
+ Distributed system, large scale system : Uber, Netflix, Grab, AirBnB, Amazon, AWS, Google, Microsoft, Facebook, Apple 
+ Resource 
    + https://eng.uber.com/ureplicator-apache-kafka-replicator/
    + https://cwiki.apache.org/confluence/display/ZOOKEEPER/PoweredBy