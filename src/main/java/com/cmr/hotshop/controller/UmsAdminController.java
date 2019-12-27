package com.cmr.hotshop.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.dto.UmsAdminLoginParam;
import com.cmr.hotshop.entity.UmsAdmin;
import com.cmr.hotshop.entity.UmsPermission;
import com.cmr.hotshop.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chenmengrui
 * @Description: 后台用户管理
 * @date 2019/12/27 14:07
 */
@Slf4j
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Response<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (ObjectUtil.isNull(umsAdmin)) {
            return Response.failed();
        }
        return Response.success(umsAdmin);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Response<Object> login(@RequestBody UmsAdminLoginParam param) {
        String token = umsAdminService.login(param.getUsername(), param.getPassword());
        if (StrUtil.isBlank(token)) {
            return Response.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = CollectionUtil.newHashMap();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Response.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限")
    @GetMapping("/permission/{adminId}")
    public Response<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        return Response.success(umsAdminService.getPermissionList(adminId));
    }

}
