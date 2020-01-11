package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.dto.OssCallbackResult;
import com.cmr.hotshop.dto.OssPolicyResult;
import com.cmr.hotshop.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenmengrui
 * @Description: Oss相关操作接口
 * @date 2020/1/11 11:06
 */
@RestController
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @GetMapping("/policy")
    public Response<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return Response.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @PostMapping("/callback")
    public Response<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return Response.success(ossCallbackResult);
    }

}
