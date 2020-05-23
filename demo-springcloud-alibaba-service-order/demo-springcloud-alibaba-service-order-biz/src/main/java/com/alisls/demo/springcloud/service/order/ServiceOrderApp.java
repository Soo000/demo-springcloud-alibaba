package com.alisls.demo.springcloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 订单服务
 *
 * @author Ke Wang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
public class ServiceOrderApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrderApp.class, args);
	}
	
}
