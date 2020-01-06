package com.cmr.hotshop.controller;

import com.cmr.hotshop.common.resp.Response;
import com.cmr.hotshop.nosql.mongo.document.MemberReadHistory;
import com.cmr.hotshop.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @PostMapping("/create")
    public Response create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return Response.success(count);
        } else {
            return Response.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @PostMapping("/delete")
    public Response delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return Response.success(count);
        } else {
            return Response.failed();
        }
    }

    @ApiOperation("展示浏览记录")
    @GetMapping("/list")
    public Response<List<MemberReadHistory>> list(Long memberId) {
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return Response.success(memberReadHistoryList);
    }

}
