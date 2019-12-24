package com.cmr.hotshop.common.request;

import lombok.Data;

/**
 * @author chenmengrui
 * @Description: 通用分页请求对象
 * @date 2019/12/24 16:41
 */
@Data
public class RequestPage {

    private int pageNum;
    private int pageSize;

}
