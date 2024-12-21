package com.pushk.kafka.consumer;

import com.pushk.kafka.payload.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "new_json_msg", groupId = "myGroup")
    public void consumeMsg(Customer msg) {
        log.info("Consuming the message from new_json_msg Topic {}", msg);
    }
}
