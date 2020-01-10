package com.cmr.hotshop.service;

import com.cmr.hotshop.dto.OrderParam;

/**
 * @author chenmengrui
 * @Description: 前台订单管理
 * @date 2020/1/10 16:36
 */
public interface OmsPortalOrderService {

    /**
     * 根据提交信息生成订单
     * @param orderParam 订单信息
     */
    void generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     * @param orderId 订单
     */
    void cancelOrder(Long orderId);

}
