# Kafka-Spring Boot: JSON Message Queue

This project demonstrates integrating **Kafka** with **Spring Boot** to send and consume JSON messages via a Kafka topic.

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
  --topic new_json_msg \
  --from-beginning \
  --bootstrap-server localhost:9092
```

---

## 📦 **Project Configuration**

### **4. Spring Boot Kafka Configuration**

Add the Kafka dependency in `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

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
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
```

---

## 💻 **Producer Service**

Create a Kafka producer to send JSON messages.

```java
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendMessage(Customer msg) {
        log.info("Sending message(JSON) to Topic {}", msg);
        Message<Customer> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "new_json_msg")
                .build();
        kafkaTemplate.send(message);
    }
}
```

---

## 📬 **Message Controller**

Expose an API endpoint to send JSON messages:

```java
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody Customer customer
    ) {
        kafkaProducer.sendMessage(customer);
        return ResponseEntity.ok("Message Queued Successfully JSON");
    }
}
```

Test the endpoint using **PowerShell**:
```powershell
Invoke-RestMethod -Uri http://localhost:8080/api/v1/messages -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"id":1,"firstname":"John","lastname":"Doe"}'
```

Test the endpoint using **cURL** on Linux/MacOS:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"id":1,"firstname":"John","lastname":"Doe"}' http://localhost:8080/api/v1/messages
```

---

## 📊 **Verify Messages in Kafka Console**

Return to the Kafka container and verify:
```bash
./kafka-console-consumer.sh \
  --topic new_json_msg \
  --from-beginning \
  --bootstrap-server localhost:9092
```

You should see:
```
Sent message: {"id":1,"firstname":"John","lastname":"Doe"}
```

---

## ✅ **Conclusion**
- Kafka setup is running with Spring Boot.
- Producer sends JSON messages to the `new_json_msg` topic.
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

---

**Authors:**
- [Your Name]

