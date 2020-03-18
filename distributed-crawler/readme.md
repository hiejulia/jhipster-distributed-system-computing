# Distributed crawler
+ Distributed—should have the ability to execute in a distributed environment across multiple machines.
+ Performance and efficiency—capable of making efficient use of system resources, i.e., processor, storage, and network bandwidth. Crawlers downloading a site for the first time is likely to download all the available archives.
+ Scalable—capable of scaling up the crawl rate by adding extra machines and bandwidth.
+ Extensible—should be designed to be extensible to cope up with new data formats and new fetch protocols. To be extensible, the crawler architecture should be modular.
+ Network-load dispersion:Multiple crawling processesof a parallel crawler may run at geographically distantlocations, each downloading “geographically-adjacent”pages. For example, a process in Germany may down-load all European pages, while another one in Japancrawls all Asian pages. In this way, we candispersethe network load to multiple regions.  In particular,this dispersion might be necessary when a single net-work cannot handle the heavy load from a large-scalecrawl.

# Technical feature 
+ Config Quartz in clustering mode for distributed scheduling backed my MySQL 





# Distributed OS, Distributed memory, Distributed file system 
# Distributing download 
+ Thread download 
+ batch download


# Caching download 

# Stack 
+ Apache Nutch - Nutch distributed and scalable, using concepts known as Hadoop and MapReduce.
    + HyperSQL 
+ Deployment : 
+ 
+ 



# More technical details 
+ 20 AWS EC2 machines 
+ HashTable
+ Distrubuted scheduling Quart in clustering mode for distributed scheduling backed by MySQL 
    + quart clock to be synced periodically 
    + task queue
    + task execution framework 


+ 60 node cluster - bulk micro instances - AWS 
+ Map Reduce framework 
+ redis + workers - AWS - Chef+ Capistrano = server herd 
+ Bloom filter = visited URLs 
+ Distributed DB : Cassandra 
Solution 1 
+ AWS : supervisor = instance (redis + script) -> send to server - micro instance - 3 workers - farm out 
    + cache DNS req - TCP connection open - fetch pages async
    + server farm : chef - config servers based on roles - capistrano - deployment 
    + algo : priority search based graph traversal for web crawling - BFS - priority queue - url - crawl next 
    + bloom filter: probabilistic 
    + redis server : track location in url frontier files 
    + hash function to thread number (similar to allocation across machines in the cluster )



# AWS 
+ high CPU EC2 instance 


# Multi thread 
+ distribute job 



# Deloyment distributed crawler 
+ 



# algorithms (crawl)
+ Bayesian classification  


# Data structure : 
+ 


# Archiecture 
+ Multithead -distributed many machines 
    + 
+ Multithread distributed to proxy servers 
+ queue
+ peer to peer : cluster and server famr 
    + p2p decentralized crawler
    + Distributed Hashtable 
    + partition crawling 
+ cloud based : Map Reduce, NoSQL db 
(no focus on centralized / hybrid crawling architecture )
+ Streaming

# Fail tolerant 
+ node failure : remove working nodes, re distributed again to working nodes. 
+ Job fail : job node failed

# References to 
+ Databook: Turning Big Data into Knowledge with Metadata at Uber
+ Netflix crawler architecture 
+ Research paper(Google, Facebook, MIT, Harvard, Stanford, IBM)
    + A Fast Distributed Focused-Web Crawling
    + Crawling Facebook for Social Network Analysis Purposes
    + SchedulingAlgorithmsforWebCrawling
    + Parallel Crawlers
    


+ Code : https://github.com/search?o=desc&q=distributed+crawl+java&s=stars&type=Repositories

