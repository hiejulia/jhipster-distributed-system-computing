# Run 
<a href="https://imgur.com/5p5SVrp"><img src="https://i.imgur.com/5p5SVrp.png" title="source: imgur.com" /></a>
+ `docker-compose up -d`
+ `docker-compose exec kafka /bin/bash`
    + create topic : `cc-authorization-topic` 
    --
    root@kafka:/# *kafka-topics --create \
        --zookeeper zookeeper:2181 \
        --partitions 3 \
        --replication-factor 1 \
        --topic cc-authorization-topic*
    Created topic "cc-authorization-topic".
    --

+ Producer 
    + build 


$ docker container run --rm \
    -v "$PWD":/home/gradle/project \
    -v "$HOME"/.gradle-docker:/root/.gradle \
    -w /home/gradle/project \
    frekele/gradle:latest gradle build

    + run producer

$ *docker container run --rm \
    --net section-1_app-net \
    -v "$PWD"/build/libs/app.jar:/app/app.jar \
    -v "$PWD"/producer.properties:/app/producer.properties \
    openjdk:11-jre-slim java -jar /app/app.jar*

+ Schema registry
    + `docker-compose exec section-1_schema-registry_1 /bin/bash*`
    + 
root@schema-registry:/# *kafka-avro-console-consumer \
    --bootstrap-server kafka:9092 \
    --from-beginning \
    --property schema.registry.url=http://schema-registry:8081 \
    --topic cc-authorization-topic*
...
{"credit_card_nbr":"cc-a0000004185","auth_time":1543328421314,"status":"OK"}
{"credit_card_nbr":"cc-a0000009249","auth_time":1543328421537,"status":"FAILED"}
{"credit_card_nbr":"cc-a0000002113","auth_time":1543328422094,"status":"OK"}
{"credit_card_nbr":"cc-a0000001879","auth_time":1543328422206,"status":"OK"}
...
--
+ Converter 
    + Exec into the KSQL CLI and connect to KSQL Server 
        + docker-compose exec ksql-cli ksql http://ksql-server:8088
            + `ksql> *SHOW topics;*`
        + Create a stream `cc_authorizations` 
            `ksql> *CREATE STREAM cc_authorizations( \
                    credit_card_nbr STRING, \
                    auth_time BIGINT, \
                    status STRING) \
                    WITH(KAFKA_TOPIC='cc-authorization-topic', VALUE_FORMAT='AVRO', KEY='credit_card_nbr');*`


            + `ksql> *set 'auto.offset.reset'='earliest';*`
            + Define this quert to discover any frauds
                + `ksql> *CREATE TABLE CC_POTENTIAL_FRAUD AS \
    SELECT credit_card_nbr, COUNT( * ) attempts \
    FROM cc_authorizations \
    WINDOW TUMBLING (SIZE 3 SECONDS) \
    WHERE status='FAILED' \
    GROUP BY credit_card_nbr \
    HAVING COUNT( * )>=3;*
`
    + `ksql> *SELECT credit_card_nbr, attempts \
    from CC_POTENTIAL_FRAUD;*
`


+ Create an intermediate stream:

`ksql> *CREATE STREAM CC_POTENTIAL_FRAUD_STREAM (\
    credit_card_nbr string, attempts bigint) \
    WITH (kafka_topic='CC_POTENTIAL_FRAUD', value_format='AVRO');*
`
+ filter stream : `ksql> *CREATE STREAM CC_POTENTIAL_FRAUD_COUNTS \
    WITH (PARTITIONS=6,REPLICAS=1) AS \
    SELECT * FROM CC_POTENTIAL_FRAUD_STREAM WHERE ROWTIME IS NOT NULL;*` 

+ Analyze the content of the underlying topic `CC_POTENTIAL_FRAUD_COUNTS`:
    `$ *docker-compose exec schema-registry /bin/bash*
root@schema-registry:/# *kafka-avro-console-consumer \
    --bootstrap-server kafka:9092 \
    --from-beginning \
    --property schema.registry.url=http://schema-registry:8081 \
    --topic CC_POTENTIAL_FRAUD_COUNTS*
{"CREDIT_CARD_NBR":{"string":"cc-a0000000345"},"ATTEMPTS":{"long":3}}
{"CREDIT_CARD_NBR":{"string":"cc-a0000001168"},"ATTEMPTS":{"long":3}}
{"CREDIT_CARD_NBR":{"string":"cc-a0000002694"},"ATTEMPTS":{"long":3}}
{"CREDIT_CARD_NBR":{"string":"cc-a0000005202"},"ATTEMPTS":{"long":3}}
...`

+ Consumer : .NET Core (SDK) 
`$ *cd ~/confluent-dev/labs/kafka-avro/dotnet/converter*
$ *docker container run --rm -it \
    --hostname dotnet \
    --net sample-app_app-net \
    -v ${HOME}/.nuget-docker:/root/.nuget \
    -v ${HOME}/.dotnet-docker/tools:/root/.dotnet/tools \
    -v $(pwd):/app \
    -w /app \
    -p 5000:5000 \
    microsoft/dotnet:2.1-sdk /bin/bash*`

+ install AvroGen tool : C# class -> Avro schema 
`root@dotnet:/app# *dotnet tool install -g Confluent.Apache.Avro.AvroGen*`

`root@dotnet:/app# *export PATH="$PATH:/root/.dotnet/tools"*`


+ `root@dotnet:/app# *avrogen -s FraudValue.cs.asvc .*`





