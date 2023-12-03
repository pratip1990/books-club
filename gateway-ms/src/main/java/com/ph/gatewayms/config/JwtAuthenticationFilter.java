package com.ph.gatewayms.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.ph.coredtos.dto.UserResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<Object> {

	@Value("${jwt.secret:helloasdasdasdasdadfdfsdfsdfasdasdawdawdasdasdwdasdaw}")
    private String secret;

    @Value("${jwt.expiration:120000}")
    private Long expiration;
	
    
    
    private WebClient webClient;

    @Autowired
    public JwtAuthenticationFilter() {
        this.webClient = WebClient.builder().baseUrl("http://localhost:9806").build();
    }
    
    @Override
    public GatewayFilter apply(Object config) {
    	log.info("Gateway called");
        return (exchange, chain) -> {
        	log.info("Gateway called");
            String token = extractToken(exchange.getRequest().getHeaders());

            if (token != null) {
                try {
                    // Validate and parse the JWT token
                    Claims claims = Jwts.parser()
                        .setSigningKey(secret) // Replace with your actual secret key
                        .parseClaimsJws(token)
                        .getBody();

                    if(isTokenExpired(claims)) {
                        throw new RuntimeException("Token Exceptions :: Expired");
                    }
                    
                    String userName = claims.getSubject();
                    if(verifiedWithUserName(userName)) {
                    	 throw new RuntimeException("Token Exceptions :: Expired");
                    }
                    
                    // Set up Spring Security context
                    exchange.getRequest().mutate().header("Authorization", "Bearer " + token);
                } catch (Exception e) {
                    // Handle invalid or expired token
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return Mono.empty();
                }
            } else {
                // No token found
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return Mono.empty();
            }

            return chain.filter(exchange);
        };
    }

    private String extractToken(HttpHeaders headers) {
        // Extract the token from the Authorization header
        String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
    
    private boolean isTokenExpired(Claims claims) {
        final Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
    
    private boolean verifiedWithUserName(String username) {
    	String validateUserUri = "/user/api/v1/get-user-username?username=" + username;

        // Use WebClient to make a GET request to the user service
        Mono<UserResponseDto> userResponseMono = webClient.get()
                .uri(validateUserUri)
                .retrieve()
                .bodyToMono(UserResponseDto.class);

        // Block and get the response
        UserResponseDto userResponse = userResponseMono.block();

        // Log the response (you might want to handle it differently based on your use case)
        log.info("Response of verifiedWithUserName: {}", userResponse);

        // Check if the response has a body
        return userResponse != null;
    }
    
    
}
