package com.beautiful.controller;

import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/ribbon")
@RestController
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @PostMapping("/create")
    public CommonResult create(@RequestBody User user){
        return restTemplate.patchForObject(userServiceUrl+"/user/create",user,CommonResult.class);
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody User user){
        return restTemplate.postForObject(userServiceUrl+"/user/update",user,CommonResult.class);
    }

    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return restTemplate.postForObject(userServiceUrl+"/user/delete/{1}",null,CommonResult.class,id);
    }

    @GetMapping("/getUser/{id}")
    public CommonResult getUser(@PathVariable("id") Long id){
        return restTemplate.getForObject(userServiceUrl+"/user/getUser/{1}",CommonResult.class,id);
    }
}
