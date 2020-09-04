package com.beautiful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Author yongjiu.dang
 * @Date 2020/9/4 13:53
 * @Version 1.0
 */
@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class TurbineServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServiceApplication.class,args);
    }
}
