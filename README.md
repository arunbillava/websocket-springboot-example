# Example for WebSockets in a Spring Boot Application

- `/topic/user` - Message Broker topic for pushing messages to the UI back.
- `/websocket/hello` - welcome url

# How to run

- `mvn clean install`
- `mvn spring-boot:run`
-  default port `8091` (application.properties)

# Set up kafka
Start zookeper & kafka:

Mac:
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties

kafka-server-start /usr/local/etc/kafka/server.properties


Window:
bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties


Create kafka topic:
Mac:
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Kafka_listner_json

kafka-console-producer --broker-list localhost:9092 --topic Kafka_listner_json


Window:

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Kafka_listner_json

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Kafka_listner_json

 