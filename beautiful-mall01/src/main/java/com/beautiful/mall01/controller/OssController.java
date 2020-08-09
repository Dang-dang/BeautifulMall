package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.dto.OssCallbackResult;
import com.beautiful.mall01.dto.OssPolicyResult;
import com.beautiful.mall01.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Api(tags = "OssController",description = "Oss管理")
@RestController
@RequestMapping("/aliyun/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("oss上传签名生成")
    @GetMapping("/policy")
    public CommonResult<OssPolicyResult> policy(){
        OssPolicyResult policy = ossService.policy();
        return CommonResult.success(policy);
    }

    @ApiOperation("oss上传成功回调")
    @PostMapping("/callback")
    public CommonResult<OssCallbackResult> callBack(HttpServletRequest request){
        log.info("callback-->执行了");
        OssCallbackResult result = ossService.callback(request);
        return CommonResult.success(result);
    }
}
