package com.beautiful.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.beautiful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping("/user")
public class UserHystrixController {

    @Autowired
    private UserService userService;

    @GetMapping("/testFallback/{id}")
    public CommonResult testFallback(@PathVariable("id") Long id){
            return userService.getUser(id);
    }

    @GetMapping("/testCommand/{id}")
    public CommonResult testCommand(@PathVariable("id") Long id){
            return userService.getUserCommand(id);
    }

    @GetMapping("/testException/{id}")
    public CommonResult testException(@PathVariable("id") Long id){
        return userService.getUserException(id);
    }

    @GetMapping("/testCache/{id}")
    public CommonResult testCache(@PathVariable("id") Long id){
        userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功",200);
    }

    @GetMapping("/testRemoveCache/{id}")
    public CommonResult testRemoveCache(@PathVariable("id") Long id){
        userService.getUserCache(id);
        userService.getUserRemoveCache(id);
        userService.getUserCache(id);
        return new CommonResult("操作成功",200);
    }

    /**
     * 测试合并请求
     */
    @GetMapping("/testCollapser")
    public CommonResult testCollapser() throws ExecutionException, InterruptedException {
        Future<User> future1=userService.getUserFuture(1L);
        Future<User> future2=userService.getUserFuture(2L);
//        future1.get();
        System.out.println(future1.get());
//        future2.get();
        System.out.println(future2.get());
        ThreadUtil.safeSleep(200);
        Future<User> future3=userService.getUserFuture(3L);
        future3.get();
//        System.out.println(future3.get());
        return new CommonResult("操作成功",200);
    }
}
