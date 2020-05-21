package com.ginko.learning.snr_integration.rabbitmq;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ginko
 * @date 5/6/20
 */
@Configuration
public class RabbitmqConfig {

    public static final String ADSB_QUEUE = "queue.ads-b.14";
    public static final MessageProperties BYTES_MESSAGE_PROPERTIES = byteProperties();

    @Bean
    public Queue declareADSBQueue() {
        return new Queue(ADSB_QUEUE, true);
    }

    private static MessageProperties byteProperties() {
        MessageProperties properties = new MessageProperties();
        return properties;
    }
}
