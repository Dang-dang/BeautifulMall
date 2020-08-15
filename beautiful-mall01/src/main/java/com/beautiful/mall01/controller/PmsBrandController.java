package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonPage;
import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.common.ResultCode;
import com.beautiful.mall01.mbg.model.PmsBrand;
import com.beautiful.mall01.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yongjiu.dang
 * @Date 2020/7/17 19:15
 * @Version 1.0
 */
@Api(tags = "PmsBrandController",description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
@Slf4j
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("查询所有品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAllBrand(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

//    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("按照页数查询品牌列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        List<PmsBrand> list=pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiOperation("添加品牌")
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

    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiOperation("更新品牌")
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

    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiOperation("根据ID删除品牌")
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

    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiOperation("根据ID查询品牌")
    @GetMapping("/getBrand/{id}")
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") Long id){
        PmsBrand brand = pmsBrandService.getBrand(id);
        return CommonResult.success(brand);
    }

}
