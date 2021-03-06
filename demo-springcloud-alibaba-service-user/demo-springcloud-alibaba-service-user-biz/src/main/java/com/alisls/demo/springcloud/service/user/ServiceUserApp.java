package com.alisls.demo.springcloud.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 用户服务
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class ServiceUserApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApp.class, args);
	}
	
}
