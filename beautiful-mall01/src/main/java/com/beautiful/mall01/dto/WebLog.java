package com.beautiful.mall01.dto;

import lombok.Data;

/**
 * controller层的日志封装
 */
@Data
public class WebLog {
    //操作描述
    private String description;
    //操作用户
    private String username;
    //操作时间
    private Long startTime;
    //消耗时间
    private Integer spendTime;
    //跟路径
    private String basePath;
    //URI
    private String uri;
    //URL
    private String url;
    //请求类型
    private String method;
    //IP地址
    private String ip;
    //请求参数
    private Object parameter;
    //返回结果
    private Object result;
}
