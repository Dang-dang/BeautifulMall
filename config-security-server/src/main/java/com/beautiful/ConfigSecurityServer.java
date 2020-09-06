package com.beautiful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigSecurityServer {
    public static void main(String[] args) {
        SpringApplication.run(ConfigSecurityServer.class,args);
    }
}
