# Prerequisites 

Docker run kafka 

Create topic 
```
sudo docker run -it --rm \
    --network kafka_default \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=kafka_zookeeper_1:2181 \
    bitnami/kafka:2 kafka-topics.sh --create --zookeeper kafka_zookeeper_1:2181 --replication-factor 1  --partitions 1 --topic general
```

List topics 
```
sudo docker run -it --rm \
    --network kafka_default \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=kafka_zookeeper_1:2181 \
    bitnami/kafka:2 kafka-topics.sh --list --zookeeper kafka_zookeeper_1:2181
```

Start consumers(for debugging)
```
sudo docker run -it --rm \
    --network kafka_default \
    -e KAFKA_CFG_ZOOKEEPER_CONNECT=kafka_zookeeper_1:2181 \
    bitnami/kafka:2 kafka-console-consumer.sh --bootstrap-server kafka_kafka_1:9092 --topic general --from-beginning
```

# Build
```
mvn package
```

# Run
```
mvn spring-boot:run
```
