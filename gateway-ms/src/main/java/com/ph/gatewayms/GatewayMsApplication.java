package com.ph.gatewayms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulServer
public class GatewayMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMsApplication.class, args);
	}

}
