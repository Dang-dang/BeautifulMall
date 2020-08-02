package com.beautiful.mall01.service.impl;

import com.beautiful.mall01.dao.EsProductDao;
import com.beautiful.mall01.nosql.elasticsearch.document.EsProduct;
import com.beautiful.mall01.nosql.elasticsearch.repository.EsProductRepository;
import com.beautiful.mall01.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private EsProductDao productDao;

    @Override
    public int importAll() {
        List<EsProduct> allEsProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProducts = productRepository.saveAll(allEsProductList);
        Iterator<EsProduct> iterator = esProducts.iterator();
        int result=0;
        while (iterator.hasNext()){
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct result=null;
        List<EsProduct> allEsProductList = productDao.getAllEsProductList(id);
        if(allEsProductList!=null&&allEsProductList.size()>0){
            EsProduct product = allEsProductList.get(0);
            result = productRepository.save(product);
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            List<EsProduct> productList=new ArrayList<>();
            for (Long id:ids) {
                EsProduct product=new EsProduct();
                product.setId(id);
                productList.add(product);
            }
            productRepository.deleteAll(productList);
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword,keyword,keyword,pageable);
    }
}
