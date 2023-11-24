/**
 * 
 */
package com.ph.userms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;


/**
 * 
 */
@Configuration
@EnableWebMvc
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("E Comm X APIs").description("API documentation for the E COM X platform"))
            .addServersItem(new Server().url("http://localhost:9092"));
    }
}