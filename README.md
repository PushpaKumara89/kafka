# Kafka-Spring Boot: String Message Queue

This project demonstrates integrating **Kafka** with **Spring Boot** to send and consume simple `String` messages via a Kafka topic.

## 🚀 **Getting Started**

### **1. Prerequisites**
- Docker & Docker Compose
- Java 17+
- Maven
- Kafka

---

### **2. Start Kafka with Docker Compose**

Ensure your `docker-compose.yml` is set up for Kafka and Zookeeper. Then run:

```bash
# Start Kafka and Zookeeper in detached mode
docker-compose up -d
```

Verify running containers:
```bash
docker ps
```

---

### **3. Check Consumed Messages in Kafka Console**

#### **Step 3.1: Access Kafka Docker Container**
```bash
docker exec -it kafka sh
```

#### **Step 3.2: Navigate to Kafka Bin Directory**
```bash
cd /opt/kafka/bin
```

#### **Step 3.3: Consume Messages from the Topic**
```bash
./kafka-console-consumer.sh \
  --topic String_msg \
  --from-beginning \
  --bootstrap-server localhost:9092
```

---

## 📦 **Project Configuration**


#### **Step 4.1: Application Configuration**
In `application.yml`:
```yaml
spring:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: myGroup
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer

    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
```

---

## 💻 **Consumer Service**


Create a Kafka producer to send messages.

```java
@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "String_msg", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info("Consuming the message from String_msg Topic {}", msg);
    }
}


```


---

## ✅ **Conclusion**
- Kafka setup is running with Spring Boot.
- Producer sends `String` messages to the `String_msg` topic.
- Kafka consumer displays the messages successfully.

---

## 🛠️ **Troubleshooting**
- Ensure Docker containers are running.
- Check Kafka logs if messages are not consumed.
- Verify `application.yml` configurations.

---

## 📄 **License**
This project is licensed under the MIT License.

---

**Happy Coding! 🚀**

