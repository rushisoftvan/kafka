<p align="center">
<picture>
  <source media="(prefers-color-scheme: light)" srcset="docs/images/kafka-logo-readme-light.svg">
  <source media="(prefers-color-scheme: dark)" srcset="docs/images/kafka-logo-readme-dark.svg">
  <img src="docs/images/kafka-logo-readme-light.svg" alt="Kafka Logo" width="50%"> 
</picture>
</p>

# kafka
[**Apache Kafka**](https://kafka.apache.org) is an open-source distributed event streaming platform used by thousands of


# usecase

 1. Scenario: E-commerce Order Processing System
 Let’s say you have an e-commerce platform with multiple services that need to interact after a customer places an order. These services include:

 Order Service - Handles order creation.
 Payment Service - Handles payment processing.
 Inventory Service - Manages inventory and updates stock.
 Shipping Service - Handles shipment of the order.
 Notification Service - Sends notifications to customers (order confirmation, shipping updates, etc.).
 Using REST API (Synchronous Approach):
 When the customer places an order, the Order Service might send a series of REST API calls to:

 Payment Service to process payment.
 Inventory Service to reduce stock.
 Shipping Service to start packing the order.
 If any of these API calls fails (e.g., payment gateway is down or inventory service is busy), the entire process might fail or slow down, which could 
 result in a poor user experience. The system is tightly coupled, and any delay in one service can affect the whole order processing flow.

 Using Messaging System (Asynchronous Approach):
Now, let’s say you switch to a messaging system like RabbitMQ or Kafka.

Order Service creates the order and sends a message (event) like "Order Placed" to a message queue.
The Payment Service listens to this queue, processes the payment, and sends a message like "Payment Successful" to another queue.
The Inventory Service listens for "Payment Successful" and then updates the stock, sending "Stock Updated" to the next service.
The Shipping Service picks up the "Stock Updated" message, and starts preparing the shipment.
Finally, the Notification Service sends a confirmation message to the customer when everything is complete.
Advantages of Messaging System Here:
Asynchronous Flow: If the Payment Service is temporarily down, the Order Service doesn’t need to wait for it. The event can be stored in the message queue and processed later when the service is back up.
ault Tolerance: If something goes wrong with one service, the message doesn’t get lost. The queue can retry or hold onto the message in a dead-letter queue, ensuring no data is lost.

Decoupling: Each service works independently. The Order Service doesn’t need to know about the specifics of the Payment Service or Shipping Service. They just listen for messages, making the system more modular.

Scalability: If the Shipping Service is processing a high volume of orders, you can scale it independently by adding more consumers to the message queue, which is much easier than scaling a REST API.

Resilience: Even if some services are temporarily unavailable, messages can be processed later without affecting the customer’s experience. In contrast, with a REST API, if one service is down, it might block the entire process until it's up again.

Example with Event-driven Flow:
Customer places order → "Order Placed" message to queue.
Payment Service processes payment → "Payment Successful" message to queue.
Inventory Service reduces stock → "Stock Updated" message to queue.
Shipping Service prepares shipment → "Shipment Ready" message to queue.
Notification Service sends customer confirmation.
By using a messaging system here, you create a more flexible, fault-tolerant, and scalable solution for processing orders, while avoiding the bottleneck or failure that might occur with direct synchronous API calls.

This kind of event-driven architecture is commonly used in microservices, where decoupling and scalability are essential.

# Apache Kafka - Terminology

   **Broker** : A Kafka broker is a server that runs Kafka. It is responsible for storing, receiving, and sending messages to clients (producers and consumers). Multiple brokers work together to form a Kafka cluster. Each broker can handle a subset of partitions from various topics.

   **Cluster** : A Kafka cluster consists of multiple Kafka brokers that work together. The cluster is responsible for maintaining the distributed nature of Kafka, allowing for data redundancy and partitioning. A Kafka cluster can handle large volumes of messages and ensure fault tolerance.

   **Producer** : A Kafka producer is a client application that sends (produces) data (messages) to Kafka topics. Producers send messages to topics in a Kafka broker. They can control where the message goes, either to a specific partition or let Kafka automatically handle it.

   **Consumer** : A Kafka consumer is a client application that reads (consumes) messages from Kafka topics. Consumers subscribe to topics and pull messages from the Kafka brokers. Kafka guarantees that each message in a topic is delivered to each consumer group (a group of consumers) at least once.

   **Topic** : A topic is a logical channel in Kafka to which producers publish messages and from which consumers consume messages. Topics in Kafka are multi-subscriber, meaning multiple consumers can consume messages from the same topic. Topics are divided into partitions for scalability.

   **Partition** : A partition is a division of a topic. Kafka stores topic messages in partitions, and each partition is a sequential log of records. Each partition is replicated across multiple brokers in a cluster to ensure fault tolerance and availability.
    Partitions allow Kafka to scale horizontally, as multiple brokers can handle different partitions of the same topic in parallel.

   **Replication** : Replication ensures fault tolerance in Kafka. Each partition in a topic has a set of replicas that are distributed across different brokers. One replica is designated as the leader, and the others are followers.
The leader is responsible for all read and write operations, while followers replicate the leader's data. If the leader broker fails, one of the followers takes over as the new leader.

 **Leader and Follower** : Leader: Each partition has one leader broker. The leader handles all reads and writes for the partition.
Follower: Each partition can have multiple replicas (followers). Followers replicate the leader's data but do not handle requests. They are used for failover and data redundancy.

**Consumer Group** : A consumer group is a group of consumers that work together to consume messages from Kafka topics. Kafka ensures that each partition of a topic is consumed by only one member of a consumer group at a time.
Consumer groups enable parallel consumption of messages. Multiple consumers in a group can consume messages from different partitions of the same topic, but each partition is only consumed by one consumer at a time.

**Offset** : Offset is a unique identifier for each message within a partition. Each message in a partition has an offset, and Kafka uses these offsets to track which messages have been consumed. Consumers use offsets to keep track of their progress.

**Message** : A message or record in Kafka consists of:
Key: An optional field used for partitioning.
Value: The actual data or payload of the message.
Timestamp: The time when the message was produced.
Offset: The unique identifier for the message within the partition.
12. Producer Acknowledgment (acks)
acks is a setting in Kafka producers that controls how many broker acknowledgments are required before considering a message successfully written:
acks=0: No acknowledgment from the broker is required. The producer does not wait for a response.
acks=1: Only the leader broker acknowledgment is required.
acks=all: All replicas of the partition must acknowledge the message.


**Rentaion** : Retention refers to the time duration that Kafka retains messages in a topic. Once a message has been retained for the specified duration or the topic reaches the configured size, Kafka will delete older messages. Retention is configured on a per-topic basis.

## Setting Up Kafka with Docker

Follow these steps to set up Kafka and Zookeeper using Docker:

1. **Download and run Docker containers for Kafka and Zookeeper**:
    ```bash
    docker run -d --name zookeeper -p 2181:2181 wurstmeister/zookeeper
    docker run -d --name kafka -p 9092:9092 --link zookeeper wurstmeister/kafka
    ```
2. ****Create a topic****:
    ```bash
    docker exec kafka-container kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4 --topic topicname
    ```
3. **List topics**:
    ```bash
    kafka-topics.sh --list --bootstrap-server localhost:9092
    ```
4. **Produce messages to the topic**:
    ```bash
    docker exec kafka-container kafka-topics --list --bootstrap-server localhost:9092  for check list
    ```
4. **Consume messages from the topic**:
    ```bash
    docker exec -it kafka-container kafka-console-consumer --bootstrap-server localhost:9092 --topic rushi --group rushi-consumer-group --from-beginning
    ``` 
    
    
        
        








   


   


   
   
    
    



 

 

