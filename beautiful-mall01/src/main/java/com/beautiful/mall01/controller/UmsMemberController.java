package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.service.UmsMemberService;
import com.beautiful.mall01.service.impl.UmsMemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UmsMemberController",description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    UmsMemberService umsMemberService;

    @ApiOperation("获取短信验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam("telephone") String telephone){
        return umsMemberService.getAuthCode(telephone);
    }

    @ApiOperation("验证验证码是否正确")
    @PostMapping("/verifyAuthCode")
    public CommonResult verifyAuthCode(@RequestParam("telephone") String telephone,
                                       @RequestParam("authCode") String authCode){
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }

}
