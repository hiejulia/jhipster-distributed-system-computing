# Distributed crawler
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



# References to 
+ Databook: Turning Big Data into Knowledge with Metadata at Uber
+ Research paper: 
    + A Fast Distributed Focused-Web Crawling
    + Crawling Facebook for Social Network Analysis Purposes
    + 




+ Code : https://github.com/search?o=desc&q=distributed+crawl+java&s=stars&type=Repositories

