package com.beautiful.service.impl;

import com.beautiful.domain.CommonResult;
import com.beautiful.domain.User;
import com.beautiful.service.UserService;
import org.springframework.stereotype.Component;

/**
 * open feign 的服务降级实现
 */
@Component
public class UserFallbackService implements UserService {
    @Override
    public CommonResult<Integer> create(User user) {
        return new CommonResult<>(-1);
    }

    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new CommonResult<>(defaultUser);
    }

    @Override
    public CommonResult delete(Long id) {
        return new CommonResult("删除失败，服务被降级",500);
    }
}
