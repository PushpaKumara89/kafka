package com.appk.ecommerce.producer;

import com.appk.ecommerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;
    public void sendMessage(Customer customer) {
        System.out.println("Message send Topic :: mytopic "+ customer);
        Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.TOPIC, "mytopic")
                .build();

        kafkaTemplate.send(message);
    }
}
