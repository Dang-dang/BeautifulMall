package com.beautiful.mall01.service;

import com.beautiful.mall01.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {
    /**
     * 从数据库导入所有商品到ES
     */
    int importAll();

    /**
     * 根据ID删除商品
     */
    void delete(Long id);

    /**
     * 根据ID创建商品
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword,Integer pageNum,Integer pageSize);
}
