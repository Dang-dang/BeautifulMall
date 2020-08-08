package com.beautiful.mall01.dto;

import lombok.Data;

@Data
public class OrderParam {
    //收获地址
    private Long memberReceiveAddressId;
    //优惠券ID
    private Long couponId;
    //使用积分数
    private Integer useIntegration;
    //支付方式
    private Integer payType;
}
