package com.ginko.learning.snr_integration.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginko
 * @date 5/6/20
 */
@Component
public class MessageSender {

    private static MessageSender instance;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        init();
    }

    public static MessageSender getInstance() {
        return instance;
    }

    public void sendBytes(String queue, byte[] msg) {
        Message message = new Message(msg, RabbitmqConfig.BYTES_MESSAGE_PROPERTIES);
        rabbitTemplate.convertAndSend(queue, message);
    }

    private void init() {
        instance = this;
    }
}
