package com.cmr.hotshop.mq;

import lombok.Getter;

/**
 * @author chenmengrui
 * @Description: 消息队列枚举配置
 * @date 2020/1/10 15:34
 */
@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("order.direct", "order.cancel", "order.cancel"),

    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("order.direct.ttl", "order.cancel.ttl", "order.cancel.ttl");

    /**
     * 交换机名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
