package com.beautiful.mall01.service;

import com.beautiful.mall01.common.CommonResult;

public interface UmsMemberService {

    CommonResult getAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone,String authCode);
}
