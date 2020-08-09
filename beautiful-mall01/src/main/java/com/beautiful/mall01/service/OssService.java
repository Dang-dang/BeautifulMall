package com.beautiful.mall01.service;

import com.beautiful.mall01.dto.OssCallbackResult;
import com.beautiful.mall01.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
