package com.beautiful.controller;

import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.beautiful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }

    @PostMapping("/delete/{id}")
    public CommonResult deleteUser(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @PostMapping("/create")
    public CommonResult createUser(@RequestBody User user){
        return userService.create(user);
    }

}
