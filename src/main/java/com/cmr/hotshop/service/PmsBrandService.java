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

    /**
     * 更新
     * @param pmsBrand
     * @return
     */
    int updateBrand(PmsBrand pmsBrand);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 查询单个详情
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);
}
