package com.cmr.hotshop.service.impl;

import com.cmr.hotshop.dto.OrderParam;
import com.cmr.hotshop.mq.OrderSender;
import com.cmr.hotshop.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenmengrui
 * @Description: 前台订单管理
 * @date 2020/1/10 16:38
 */
@Slf4j
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Autowired
    private OrderSender orderSender;

    @Override
    public void generateOrder(OrderParam orderParam) {
        //todo 执行一系类下单操作
        log.info("process generateOrder");
        //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        orderSender.sendMessage(11L, 30 * 1000);
    }

    @Override
    public void cancelOrder(Long orderId) {
        log.info("cancel order ： {}", orderId);
    }

}
