package com.nturbo1.task_service.service.implementations;

import com.nturbo1.task_service.service.interfaces.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducerImpl implements MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
        log.debug("MessageProducerImpl.sendMessage(String message) ------- message: {}", message);
        rabbitTemplate.convertAndSend("task-due-exchange", "task.due.key", message);
        log.debug("MessageProducerImpl.sendMessage(String message) ------- Message sent successfully");
    }
}
