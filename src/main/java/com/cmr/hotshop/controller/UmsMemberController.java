package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenmengrui
 * @Description: 会员登录注册管理
 * @date 2019/12/26 9:52
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public Response<String> getAuthCode(@RequestParam("telephone") String telephone) {
        return Response.success(umsMemberService.generateAuthCode(telephone));
    }

    @ApiOperation("校验验证码")
    @GetMapping("/verifyAuthCode")
    public Response<Boolean> verifyAuthCode(@RequestParam String telephone,
                                            @RequestParam String authcode) {
        boolean result = umsMemberService.verifyAuthCode(telephone, authcode);
        if (result) {
            return Response.success();
        }
        return Response.failed();
    }

}
