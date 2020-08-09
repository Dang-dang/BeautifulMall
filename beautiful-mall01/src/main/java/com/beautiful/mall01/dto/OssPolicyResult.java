package com.beautiful.mall01.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取oss上传文件授权返回的结果
 */
@Data
public class OssPolicyResult {
    @ApiModelProperty("访问身份验证中的用户标识")
    private String accessKeyId;
    @ApiModelProperty("用户表单上传的策略，经过base64编码过的字符串")
    private String policy;
    @ApiModelProperty("对policy签名后的字符串")
    private String signature;
    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;
    @ApiModelProperty("oss对外服务器的域名")
    private String host;
    @ApiModelProperty("上传成功后的回调设置")
    private String callback;
}
