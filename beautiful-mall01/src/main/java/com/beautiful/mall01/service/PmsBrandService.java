package com.beautiful.mall01.service;

import com.beautiful.mall01.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id,PmsBrand pmsBrand);

    int deleteBrand(Long id);

    PmsBrand getBrand(Long id);

    List<PmsBrand> listBrand(int pageNum,int pageSize);



}
