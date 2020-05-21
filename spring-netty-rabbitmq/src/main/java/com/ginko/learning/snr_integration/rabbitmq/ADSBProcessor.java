package com.ginko.learning.snr_integration.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ginko
 * @date 5/6/20
 */
@Component
public class ADSBProcessor {

    @RabbitListener(queues = {RabbitmqConfig.ADSB_QUEUE}, concurrency = "5")
    public void process(Channel channel, Message message) {
        try {
            // process here

            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                if (message.getMessageProperties().getRedelivered()) {
                    // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                } else {
                    // requeue为是否重新回到队列，true重新入队
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
            } catch (Exception ex) {
                //log here
            }
        }
    }
}
