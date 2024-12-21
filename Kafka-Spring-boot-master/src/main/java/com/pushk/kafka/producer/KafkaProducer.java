package com.pushk.kafka.producer;

import com.pushk.kafka.payload.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendMessage(Customer msg){
        log.info("Sending message(String) to Topic {} ", msg);
        Message<Customer> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, "new_json_msg")
                .build();
//        kafkaTemplate.send("String_msg", msg);
    }
}
