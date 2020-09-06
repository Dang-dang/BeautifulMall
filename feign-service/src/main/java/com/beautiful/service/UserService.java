package com.beautiful.service;

import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.beautiful.service.impl.UserFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * openfeign 将Ribbon和Hystrix 做了一个整合，既可以实现负载均衡也可以实现服务降级处理
 */
@FeignClient(value = "user-service",fallback = UserFallbackService.class)
public interface UserService {

    @PostMapping("/user/create")
    CommonResult<Integer> create(@RequestBody User user);

    @GetMapping("/user/getUser/{id}")
    CommonResult<User> getUser(@PathVariable("id") Long id);

    @PostMapping("/user/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);

}
