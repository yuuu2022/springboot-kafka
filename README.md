
# Springboot Kafka

There are some steps to create 3 broker using command line .
1. dowload latest kafka tar (The project version is using kafaka_2.11-1.0.0) 
2. unzip the tar file and go into the kafka folder
3. create etc folder (cmd : mkdir etc)
4. copy zookeeper to the folder we created (cmd : config/zookeeper.properties etc)

5. copy server properties to etc folder because server file control the port and broker (cp config/server.properties etc/server-0.properties , cp config/server.properties etc/server-1.properties, cp config/server.properties etc/server-2.properties)

6. update the properties file that we created as below items 
    #1. broker id 
    #2. listen port 
    #3. log directory 

7. start zookeeper to manage the whole kafaka (to be broker ,data checker , maintance) (cmd: ./bin/zookeeper-server-start.sh ./etc/zookeeper.properties)

8. start all broker (cmd : ./bin/kafka-server-start.sh ../etc/server-0.properties , cmd : ./bin/kafka-server-start.sh ../etc/server-1.properties,cmd : ./bin/kafka-server-start.sh ../etc/server-2.properties)

9. create topic (cmd: ./bin/kafka-topics.sh --zookeeper localhost:2181 -- create --topic test --partitions 3 --replication-factor 2)

10. check the topic we created (cmd : ./bin/kafka-topic.sh --zookeeper localhost:2181 --describe --topic test)

11. try the receive message by consumer (cmd ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic test)
12. try the send message by client
(cmd : ./bin/kafka-console.producer.sh --broker-list localhost:9092,localhost:9093,localhost:9094)


There are some steps to start kafaka by docker :

docker compose up -d

#create top
docker exec -it kafka-0 /opt/bitnami/kafka/bin/kafka-topics.sh \
--create --bootstrap-server kafka-0:9092 \
--topic my-topic \
--partitions 3 --replication-factor 2

#create producer then input your message 
docker exec -it kafka-0 /opt/bitnami/kafka/bin/kafka-console-producer.sh \
--bootstrap-server kafka-0:9092 \
--topic my-topic

#create consumer
docker exec -it kafka-0 /opt/bitnami/kafka/bin/kafka-console-consumer.sh \
--bootstrap-server kafka-0:9092 \
--topic my-topic
