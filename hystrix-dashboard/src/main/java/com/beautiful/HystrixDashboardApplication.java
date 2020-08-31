package com.beautiful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author yongjiu.dang
 * @Date 2020/8/31 16:30
 * @Version 1.0
 */
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class,args);
    }
}

/*
hystrix dashboard 界面一直loading  按F12看到下边这些报错信息 这是Honton.SR5 依赖的jquery 版本冲突
参考博客：https://blog.csdn.net/weixin_43493520/article/details/106646860?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.add_param_isCf
解决方法：排除Honton.SR5默认的dash board 2.2.3的依赖使用2.2.2的依赖
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-netflix-hystrix-dashboard</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-netflix-hystrix-dashboard</artifactId>
            <version>2.2.2.RELEASE</version>
        </dependency>
问题得以解决
* jquery.min.js:2 Uncaught TypeError: e.indexOf is not a function
    at k.fn.init.k.fn.load (jquery.min.js:2)
    at monitor?stream= http%3A%2F%2Flocalhost%3A8401%2Factuator%2Fhystrix.stream&title=qqq:110
* */
