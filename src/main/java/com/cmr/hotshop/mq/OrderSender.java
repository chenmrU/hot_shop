package com.cmr.hotshop.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenmengrui
 * @Description: 发送订单消息
 * @date 2020/1/10 16:03
 */
@Slf4j
@Component
public class OrderSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送
     * @param orderId 订单id
     * @param delayTimes 延迟时间 ms
     */
    public void sendMessage(Long orderId, final long delayTimes) {
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, message -> {
                    //给消息设置延迟时间
                    message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return message;
                });
        log.info("Send delay message orderId : {}", orderId);
    }

}
