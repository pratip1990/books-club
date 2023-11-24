package com.ph.authms.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ph.coredtos.dto.LoginResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private int jwtExpirationMs;

	@Value("${jwt.expiration-refresh}")
	private int jwtRefreshExpirationMs;
	
	private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    
	@SuppressWarnings("deprecation")
	public String generateJwtToken(String username) {
		Date tokenCreateTime = new Date();
		Date expiration = new Date(tokenCreateTime.getTime() + jwtExpirationMs);
		
		 Claims claims = Jwts.claims().setSubject(username);
	        return Jwts.builder()
	                .setClaims(claims)
	                .setExpiration(expiration)
	                .signWith(SignatureAlgorithm.HS256, jwtSecret)
	                .compact();
	}

	@SuppressWarnings("deprecation")
	public String generateRefreshToken(String username) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + jwtRefreshExpirationMs);

		Map<String, Object> claims = new HashMap<>();
		claims.put("type", "refresh");

		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiration).addClaims(claims)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	@SuppressWarnings("deprecation")
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	@SuppressWarnings("deprecation")
	public LoginResponseDto	 validateJwtToken(String authToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
			log.info("claims : {}", claims);
			return null;
		} catch (Exception e) {
			log.error("error in validate token :: ", e);
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean validateRefreshToken(String refreshToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(refreshToken).getBody();
			return "refresh".equals(claims.get("type", String.class));
		} catch (Exception e) {
			log.error("error in validate refresh token :: ", e);
			return false;
		}
	}
}
	