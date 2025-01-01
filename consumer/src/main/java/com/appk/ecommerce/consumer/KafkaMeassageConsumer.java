package com.appk.ecommerce.consumer;

import com.appk.ecommerce.model.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMeassageConsumer {

    @KafkaListener(groupId = "myGroup", topics = "mytopic")
    public void messageReceive(@Payload Customer customer) {
        System.out.println("message receive from kafka :: "+customer);
    }
}
