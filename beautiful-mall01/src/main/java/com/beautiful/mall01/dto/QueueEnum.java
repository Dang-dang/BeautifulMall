package com.beautiful.mall01.dto;

import lombok.Getter;

@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl");
    /**
     * 交换机名
     */
    private String exchange;
    /**
     * 队列名
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange,String name,String routeKey){
        this.exchange=exchange;
        this.name=name;
        this.routeKey=routeKey;
    }
}
