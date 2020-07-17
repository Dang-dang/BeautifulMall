package com.beautiful.mall01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yongjiu.dang
 * @Date 2020/7/17 19:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @GetMapping("/hello")
    public String getbrand(){
        return "hello world";
    }

}
