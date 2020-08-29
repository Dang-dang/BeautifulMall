package com.beautiful.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> {

    private T data;
    private String message;
    private Integer code;

    public CommonResult(String message,Integer code){
        this(null,message,code);
    }

    public CommonResult(T data){
        this(data,"操作成功",200);
    }
}
