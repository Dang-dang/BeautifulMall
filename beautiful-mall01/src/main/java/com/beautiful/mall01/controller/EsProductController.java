package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonPage;
import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.nosql.elasticsearch.document.EsProduct;
import com.beautiful.mall01.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "EsProductController",description = "搜索商品管理")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping("/importAll")
    public CommonResult<Integer> importAll(){
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据ID删除商品")
    @DeleteMapping("delete/{id}")
    public CommonResult<Object> delete(@PathVariable("id") Long id){
        esProductService.delete(id);
        return CommonResult.success(null,"删除成功");
    }

    @ApiOperation(value = "根据ID创建商品")
    @PostMapping("/create/{id}")
    public CommonResult<EsProduct> create(@PathVariable("id") Long id){
        EsProduct esProduct = esProductService.create(id);
        if(esProduct!=null){
            return CommonResult.success(esProduct);
        }else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据id批量删除")
    @PostMapping("/delete/batch")
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids){
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping("/search/simple")
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(value = "keyword",required = false) String keyword,
                                                      @RequestParam(value = "pageNum",required = false,defaultValue = "0") Integer pageNum,
                                                      @RequestParam(value = "pageSize",required = false,defaultValue = "5") Integer pageSize){
        Page<EsProduct> search = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(search));
    }
}
