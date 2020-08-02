package com.beautiful.mall01.controller;

import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.mbg.model.UmsAdmin;
import com.beautiful.mall01.mbg.model.UmsPermission;
import com.beautiful.mall01.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "UmsAdminController",description = "后台用户管理")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin, BindingResult result){
        UmsAdmin register = umsAdminService.register(umsAdmin);
        if(register==null){
            return CommonResult.failed();
        }
        return CommonResult.success(register);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@RequestBody UmsAdmin umsAdmin,BindingResult result){
        String token = umsAdminService.login(umsAdmin.getUsername(), umsAdmin.getPassword());
        if(StringUtils.isEmpty(token)){
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取用户所有权限")
    @GetMapping("/permission/{id}")
    public CommonResult<List<UmsPermission>> getListPermissions(@PathVariable("id") Long id){
        List<UmsPermission> permissions = umsAdminService.getPermissionList(id);
        return CommonResult.success(permissions);
    }
}
