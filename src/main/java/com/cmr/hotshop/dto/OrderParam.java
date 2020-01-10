package com.cmr.hotshop.dto;

import lombok.Data;

/**
 * @author chenmengrui
 * @Description: 生成订单时传入的参数
 * @date 2020/1/10 16:44
 */
@Data
public class OrderParam {

    /**
     * 收货地址id
     */
    private Long memberReceiveAddressId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 使用的积分数
     */
    private Integer useIntegration;

    /**
     * 支付方式
     */
    private Integer payType;

}
