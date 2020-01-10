package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.dto.OrderParam;
import com.cmr.hotshop.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenmengrui
 * @Description: 订单管理Controller
 * @date 2020/1/10 16:41
 */
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RestController("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService omsPortalOrderService;

    @ApiOperation("根据购物车信息生成订单")
    @PostMapping("/generateOrder")
    public Response generateOrder(@RequestBody OrderParam orderParam) {
        omsPortalOrderService.generateOrder(orderParam);
        return Response.success();
    }

}
