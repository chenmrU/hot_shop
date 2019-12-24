package com.cmr.hotshop.service.impl;

import com.cmr.hotshop.common.request.RequestPage;
import com.cmr.hotshop.dao.PmsBrandMapper;
import com.cmr.hotshop.entity.PmsBrand;
import com.cmr.hotshop.entity.PmsBrandExample;
import com.cmr.hotshop.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenmengrui
 * @Description: 品牌管理服务
 * @date 2019/12/24 15:07
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Resource
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAllBrand(RequestPage requestPage) {
        PageHelper.startPage(requestPage.getPageNum(), requestPage.getPageSize());
        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        return pmsBrandMapper.selectByExample(pmsBrandExample);
    }

    @Override
    public int createBrand(PmsBrand pmsBrand) {
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

}
