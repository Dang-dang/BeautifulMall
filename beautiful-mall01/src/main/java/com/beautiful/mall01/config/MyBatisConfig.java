package com.beautiful.mall01.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yongjiu.dang
 * @Date 2020/7/17 18:53
 * @Version 1.0
 */

@MapperScan({"com.beautiful.mall01.mbg.mapper","com.beautiful.mall01.dao"})
@Configuration
public class MyBatisConfig {
}
