kubectl run kafka-producer --image=solsson/kafka:0.11.0.0 --rm -it --command -- \
    ./bin/kafka-console-producer.sh --broker-list kafka-service-kafka:9092 \
    --topic users-1



kubectl run kafka-consumer --image=solsson/kafka:0.11.0.0 --rm -it --command -- \
    ./bin/kafka-console-consumer.sh --bootstrap-server kafka-service-kafka:9092\
    --topic users-1 \
        --from-beginning