package com.nturbo1.notification_service.service;

import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    public void consumeMessage(String message) {
        System.out.println("Message received: " + message);
    }
}
