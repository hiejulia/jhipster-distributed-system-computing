# Reactive - Actor
+ Customer - Movie - Production Company 

# Stack 
+ Scala 
+ Akka 
+ Lagom 
+ Play framework 


# Distributed system 
+ Streaming 
    + micro batch 
    + source sink 
    + flow 
    + fan in - fan out - Bidiflow 
+ Test concurrent behaviour with Akka TestKit 




# Production pipeline 
+ Security 
    + VPN 
    + Firewall 
    + HTTPS
    + encrypt transport 
+ Log/ trace: OpenTracing, Kamon, Lightbend monitor 
+ Run time env
+ Assemble app for deployment to a cloud container service 



<a href="https://imgur.com/BKUYxlB"><img src="https://i.imgur.com/BKUYxlB.png" title="source: imgur.com" /></a>

# Run
+ `sbt run`
+ Run multiple actor system 
    + sbt "-Dakka.remote.netty.tcp.port=2553" "runMain .."     
    sbt "-Dakka.remote.netty.tcp.port=2554" "runMain .."     
    sbt "-Dakka.remote.netty.tcp.port=2555" "runMain .."    


# Distributed actors over multiple system
+ Splitting into multiple actor systems 


# 
+ conf
+ HOCON 

