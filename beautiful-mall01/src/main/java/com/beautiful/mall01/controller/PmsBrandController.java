package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonPage;
import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.common.ResultCode;
import com.beautiful.mall01.mbg.model.PmsBrand;
import com.beautiful.mall01.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yongjiu.dang
 * @Date 2020/7/17 19:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/brand")
@Slf4j
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAllBrand(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<PmsBrand> list=pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PostMapping("/create")
    public CommonResult<PmsBrand> createBrand(@RequestBody PmsBrand pmsBrand){
        int code=pmsBrandService.createBrand(pmsBrand);
        if(code==1){
            log.info("createBrand 成功");
            return CommonResult.success(pmsBrand);
        }else {
            log.info("createBrand 失败");
            return CommonResult.failed();
        }
    }

    @PostMapping("/update/{id}")
    public CommonResult<PmsBrand> updateBrand(@PathVariable("id") Long id,@RequestBody PmsBrand pmsBrand){
        int code=pmsBrandService.updateBrand(id,pmsBrand);
        if(code==1){
            log.info("createBrand 成功");
            return CommonResult.success(pmsBrand);
        }else {
            log.info("createBrand 失败");
            return CommonResult.failed();
        }
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id){
        int code = pmsBrandService.deleteBrand(id);
        if(code==1){
            log.info("deleteBrand 成功");
            return CommonResult.success(null);
        }else {
            log.info("deleteBrand 失败");
            return CommonResult.failed();
        }
    }

    @GetMapping("/getBrand/{id}")
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") Long id){
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommonResult.success(brand);
    }

}
