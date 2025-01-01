package com.appk.ecommerce.rest;

import com.appk.ecommerce.model.Customer;
import com.appk.ecommerce.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@CrossOrigin
public class MessageController {
    @Autowired
    private MessageProducer producer;

    @PostMapping
    public String sendMessage(@RequestBody Customer msg) {
        producer.sendMessage(msg);
        return "message add queued......!";
    }
}
