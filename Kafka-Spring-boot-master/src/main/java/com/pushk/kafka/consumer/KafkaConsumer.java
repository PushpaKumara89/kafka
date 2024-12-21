package com.pushk.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "String_msg", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info("Consuming the message from String_msg Topic {}", msg);
    }
}
