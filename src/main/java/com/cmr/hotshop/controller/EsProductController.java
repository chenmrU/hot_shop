package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.common.resp.ResponsePage;
import com.cmr.hotshop.nosql.elasticsearch.document.EsProduct;
import com.cmr.hotshop.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "EsProductController", description = "搜索商品管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("/importAll")
    public Response<Integer> importAllList() {
        int count = esProductService.importAll();
        return Response.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @GetMapping("/delete/{id}")
    public Response<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return Response.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @PostMapping("/delete/batch")
    public Response<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return Response.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping("/create/{id}")
    public Response<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return Response.success(esProduct);
        } else {
            return Response.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping("/search/simple")
    public ResponsePage<EsProduct> search(@RequestParam(required = false) String keyword,
                                          @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return ResponsePage.restPage(esProductPage.getContent());
    }
}
