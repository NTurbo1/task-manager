package com.nturbo1.notification_service.config.rabbitmq;

import com.nturbo1.notification_service.service.MessageConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.topic-exchange.task-due.name}")
    private String taskDueTopicExchange;
    @Value("${rabbitmq.queue.name}")
    private String taskDueQueue;

    @Bean
    public Queue taskDueQueue() {
        return new Queue(taskDueQueue, true);
    }

    @Bean
    public TopicExchange taskDueTopicExchange() {
        return new TopicExchange(taskDueTopicExchange);
    }

    @Bean
    public Binding taskDueBinding(Queue taskDueQueue, TopicExchange taskDueTopicExchange) {
        return BindingBuilder.bind(taskDueQueue).to(taskDueTopicExchange).with("task.due.key");
    }

    @Bean
    public SimpleMessageListenerContainer taskDueContainer(ConnectionFactory connectionFactory,
                                                           MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(taskDueQueue);
        container.setMessageListener(messageListenerAdapter);

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(MessageConsumer messageConsumer) {
        return new MessageListenerAdapter(messageConsumer, "consumeMessage");
    }
}
