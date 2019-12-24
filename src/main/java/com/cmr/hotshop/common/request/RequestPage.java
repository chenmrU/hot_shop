package com.cmr.hotshop.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenmengrui
 * @Description: 通用分页请求对象
 * @date 2019/12/24 16:41
 */
@ApiModel
@Data
public class RequestPage {

    @ApiModelProperty("页码")
    private int pageNum;

    @ApiModelProperty("每页数量")
    private int pageSize;

}
