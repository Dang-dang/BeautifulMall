package com.beautiful.mall01.service.impl;

import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.component.CancelOrderSender;
import com.beautiful.mall01.dto.OrderParam;
import com.beautiful.mall01.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Autowired
    private CancelOrderSender sender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        //TODO 执行一系列下单操作
        log.info("下单生成订单号");
        //下单后开启一个延迟消息，超过延迟时间没付款自动取消订单,传入生成的订单ID
        sendDelayMessageCancelOrder(10L);
        return CommonResult.success(null,"下单成功");
    }

    private void sendDelayMessageCancelOrder(Long id){
        //订单超时时间 5分钟
        long delayTime=2*60*1000;
        //发送延迟消息
        sender.sendMessage(id,delayTime);
    }

    @Override
    public void cancelOrder(Long orderId) {
        //TODO 执行一系列取消订单操作
        log.info("订单取消了，orderId:{}",orderId);
    }
}
