package com.fangxing.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



//让该应用启动断路器功能
@EnableCircuitBreaker
//让该应用注册为Eureka客户端应用
@EnableDiscoveryClient
@SpringBootApplication
//可以将上面的3个注解合并成一个
//@SpringCloudApplication
public class RibbonConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

	@Bean
	//通过这样的注解，RestTemplate接可以使用到Ribbon的负载均衡功能
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
