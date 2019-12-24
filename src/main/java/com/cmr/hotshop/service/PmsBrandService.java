package com.cmr.hotshop.service;

import com.cmr.hotshop.common.request.RequestPage;
import com.cmr.hotshop.entity.PmsBrand;

import java.util.List;

/**
 * @author chenmengrui
 * @Description: 品牌管理服务
 * @date 2019/12/24 15:05
 */
public interface PmsBrandService {

    /**
     * 查询品牌列表
     * @return
     */
    List<PmsBrand> listAllBrand(RequestPage requestPage);

    /**
     * 新增品牌
     * @param pmsBrand
     * @return
     */
    int createBrand(PmsBrand pmsBrand);

}
