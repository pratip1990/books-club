package com.ph.gatewayms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.ph.gatewayms.config.JwtAuthenticationConfig;
import com.ph.gatewayms.config.JwtAuthenticationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMsApplication {
	
	@Value("${jwt.secret:helloasdasdasdasdadfdfsdfsdfasdasdawdawdasdasdwdasdaw}")
    private String secret;

    @Value("${jwt.expiration:120000}")
    private Long expiration;
	
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayMsApplication.class, args);
	}
	
	@Bean
    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
        return routeLocatorBuilder.routes() 
                        .route("USER-MS",r->r.path("/user/**")
                        		.filters(f -> f.filter(jwtAuthenticationFilter
                        				.apply(new JwtAuthenticationConfig(secret, expiration))))
                                .uri("http://localhost:9806"))
                        .build(); 
    } 
	
	/*
	 * @Bean public WebClient.Builder webClientBuilder() { return
	 * WebClient.builder(); }
	 */
}
