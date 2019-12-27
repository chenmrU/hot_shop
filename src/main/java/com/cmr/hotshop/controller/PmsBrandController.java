package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.request.RequestPage;
import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.common.resp.ResponsePage;
import com.cmr.hotshop.entity.PmsBrand;
import com.cmr.hotshop.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenmengrui
 * @Description: 品牌管理
 * @date 2019/12/24 14:47
 */
@Slf4j
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Resource
    private PmsBrandService pmsBrandService;

    @ApiOperation("获取所有品牌列表")
    @PostMapping("listAll")
    @PreAuthorize("hasAuthority('pms:brand:read')")
    public ResponsePage<PmsBrand> getBrandList(@RequestBody RequestPage requestPage) {
        return ResponsePage.restPage(pmsBrandService.listAllBrand(requestPage));
    }

    @ApiOperation("添加品牌")
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

    @ApiOperation("更新指定id品牌信息")
    @PostMapping("/update/{id}")
    public Response updateBrand(@RequestBody PmsBrand pmsBrand) {
        Response response;
        int count = pmsBrandService.updateBrand(pmsBrand);
        if (count == 1) {
            response = Response.success(pmsBrand);
            log.debug("updateBrand success:{}", pmsBrand);
        } else {
            response = Response.failed();
            log.debug("updateBrand failed:{}", pmsBrand);
        }
        return response;
    }

    @ApiOperation("删除指定id的品牌")
    @GetMapping("/delete/{id}")
    public Response deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            log.debug("deleteBrand success :id={}", id);
            return Response.success();
        } else {
            log.debug("deleteBrand failed :id={}", id);
            return Response.failed();
        }
    }

    @ApiOperation("获取指定id的品牌详情")
    @GetMapping("/{id}")
    public Response<PmsBrand> brand(@PathVariable("id") Long id) {
        return Response.success(pmsBrandService.getBrand(id));
    }

}
