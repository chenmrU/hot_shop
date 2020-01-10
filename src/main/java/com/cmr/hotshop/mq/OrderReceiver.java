package com.cmr.hotshop.mq;

import com.cmr.hotshop.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenmengrui
 * @Description: 取消订单消息的处理者
 * @date 2020/1/10 16:27
 */
@Slf4j
@Component
@RabbitListener(queues = "order.cancel")
public class OrderReceiver {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @RabbitHandler
    public void handle(Long orderId) {
        log.info("receive delay message orderId:{}",orderId);
        omsPortalOrderService.cancelOrder(orderId);
    }

}
