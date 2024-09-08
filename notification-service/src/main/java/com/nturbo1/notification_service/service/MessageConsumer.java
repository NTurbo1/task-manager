package com.nturbo1.notification_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageConsumer {

    public void consumeMessage(String message) {
        log.debug("MessageConsumer.consumeMessage(String message) ------- message: {}", message);
    }
}
