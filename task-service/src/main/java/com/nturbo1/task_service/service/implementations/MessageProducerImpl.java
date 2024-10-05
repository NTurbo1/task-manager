package com.nturbo1.task_service.service.implementations;

import com.nturbo1.task_service.service.interfaces.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProducerImpl implements MessageProducer {

  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.topic-exchange.task-due.name}")
  private String taskDueTopicExchangeName;

  @Value("${rabbitmq.routing-key.task-due}")
  private String taskDueRoutingKey;

  @Override
  public void sendMessage(String message) {
    log.debug("MessageProducerImpl.sendMessage(String message) ------- message: {}", message);
    rabbitTemplate.convertAndSend(taskDueTopicExchangeName, taskDueRoutingKey, message);
    log.debug("MessageProducerImpl.sendMessage(String message) ------- Message sent successfully");
  }
}
