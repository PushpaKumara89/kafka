# Kafka Producer and Consumer Projects

## 📚 Overview
This repository contains two separate Spring Boot applications:

1. **Producer Service:** Publishes `Customer` messages to a Kafka topic.
2. **Consumer Service:** Listens to Kafka topic messages and processes them.

---

## 🚀 1. Producer Service

### 📦 Description
The **Producer Service** sends `Customer` data as JSON messages to a Kafka topic named `mytopic`.

### ⚙️ Configuration

**application.yml:**
```yaml
spring:
  kafka:
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        spring.json.type.mapping: customer:com.appk.ecommerce.model.Customer
```

### 📝 Customer Model
```java
package com.appk.ecommerce.model;

public class Customer {
    private Long id;
    private String name;
    private String email;

    // Getters and Setters
}
```

### 📤 Message Producer Example
```java
package com.appk.ecommerce.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendMessage(Customer customer) {
        kafkaTemplate.send("mytopic", customer);
    }
}
```

### ▶️ Run the Producer
1. Start Kafka Broker (`localhost:9092`).
2. Run the Producer application:
   ```bash
   mvn spring-boot:run
   ```
3. Use an API or script to send `Customer` objects.

---

## 🚀 2. Consumer Service

### 📦 Description
The **Consumer Service** listens to messages from the Kafka topic `mytopic` and processes `Customer` data.

### ⚙️ Configuration

**application.yml:**
```yaml
spring:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      auto-offset-reset: earliest
      group-id: myGroup
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "*"
        spring.json.type.mapping: customer:com.appk.ecommerce.model.Customer
```

### 📝 Kafka Listener Example
```java
package com.appk.ecommerce.consumer;

import com.appk.ecommerce.model.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "mytopic", groupId = "myGroup")
    public void messageReceive(Customer customer) {
        System.out.println("Received Customer: " + customer);
    }
}
```

### ▶️ Run the Consumer
1. Start Kafka Broker (`localhost:9092`).
2. Run the Consumer application:
   ```bash
   mvn spring-boot:run
   ```
3. Verify the logs for incoming `Customer` messages.

---

## 🧪 Testing the Integration
1. Start **Kafka Broker**, **Producer**, and **Consumer** services.
2. Send a test message from the Producer:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "email": "john.doe@example.com"
   }
   ```
3. Verify the Consumer logs:
   ```
   Received Customer: Customer{id=1, name='John Doe', email='john.doe@example.com'}
   ```

---

## 🐞 Troubleshooting

- **ListenerExecutionFailedException:** Ensure `spring.json.type.mapping` matches in both applications.
- **Serialization/Deserialization Issues:** Verify `Customer` class structure.
- Enable debug logs:
```yaml
logging:
  level:
    org.springframework.kafka: DEBUG
```

---

## 📚 Dependencies
- Spring Boot Starter Kafka
- Apache Kafka
- Java 17+

---

## 📄 License
This project is licensed under the MIT License.

---

## 🙌 Contributing
Feel free to raise issues or submit pull requests.

---

Happy Coding! 🎉🚀

