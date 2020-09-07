package com.beautiful.controller;

import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.beautiful.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult<Integer> create(@RequestBody User user){
        log.info("create-->>");
        return new CommonResult<Integer>(userService.create(user));
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user){
        userService.update(user);
        log.info("update-->>");
        return new CommonResult("更新成功",200);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        userService.delete(id);
        log.info("delete-->>");
        return new CommonResult("删除成功",200);
    }

    @GetMapping("/getUser/{id}")
    public CommonResult<User> getUser(@PathVariable("id") Long id){
        log.info("get-->>");
        return new CommonResult<>(userService.getUser(id));
    }

    //请求路径如果是getUserByIds时，末尾和参数ids重合会导致传过来的ids参数识别不到
    @GetMapping("/getUserByIdsList")
    public CommonResult<List<User>> getUserByIds(@RequestParam("ids") List<Long> ids){
        log.info("getUserByIds-->>"+ids);
        return new CommonResult<>(userService.getUserByIds(ids));
    }

    @GetMapping("/test")
    public void test(@RequestParam("ids") List<Long> name){
        log.info("getUserByIds-->>"+name);
    }
}
