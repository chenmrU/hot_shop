package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.request.RequestPage;
import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.common.resp.ResponsePage;
import com.cmr.hotshop.entity.PmsBrand;
import com.cmr.hotshop.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenmengrui
 * @Description: 品牌管理
 * @date 2019/12/24 14:47
 */
@Slf4j
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Resource
    private PmsBrandService pmsBrandService;

    @PostMapping("listAll")
    public ResponsePage<PmsBrand> getBrandList(@RequestBody RequestPage requestPage) {
        return ResponsePage.restPage(pmsBrandService.listAllBrand(requestPage));
    }

    @PostMapping("/create")
    public Response createBrand(@RequestBody PmsBrand pmsBrand) {
        Response response;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            response = Response.success(pmsBrand);
            log.debug("Create brand success: {}", pmsBrand);
        } else {
            response = Response.failed();
            log.debug("Create brand failed: {}", pmsBrand);
        }
        return response;
    }

}
