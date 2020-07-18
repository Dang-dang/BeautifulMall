package com.beautiful.mall01.service.impl;

import com.beautiful.mall01.common.CommonResult;
import com.beautiful.mall01.service.RedisService;
import com.beautiful.mall01.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long REDIS_AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult getAuthCode(String telephone) {
        String s = UUID.randomUUID().toString().substring(0, 4);
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,s);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,REDIS_AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(s,"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        String s = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if(!StringUtils.isEmpty(authCode)&&s.equals(authCode)){
            return CommonResult.success(null,"验证码校验成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
