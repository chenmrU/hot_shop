package com.cmr.hotshop.dao;

import com.cmr.hotshop.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 */
public interface EsProductMapper {

    List<EsProduct> getAllEsProductList(@Param("id") Long id);

}
