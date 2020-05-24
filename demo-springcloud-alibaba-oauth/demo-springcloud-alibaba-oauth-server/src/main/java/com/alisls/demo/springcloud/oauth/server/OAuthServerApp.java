package com.alisls.demo.springcloud.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 认证服务器
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableFeignClients
public class OAuthServerApp {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApp.class, args);
    }

}
