/**
 * 
 */
package com.ph.authms.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ph.authms.config.JwtUtils;
import com.ph.authms.service.AuthService;
import com.ph.coredtos.dto.AuthTokenDto;
import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;

/**
 * 
 */

@Service
public class AuthServiceImpl implements AuthService {

	private final JwtUtils jwtUtils;

	private RestTemplate restTemplate;

	@Autowired
	public AuthServiceImpl(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
		this.restTemplate = new RestTemplate();
	}

	@Override
	public ResponseEntity<AuthTokenDto> generateToken(LoginRequestDto logRequest) {
		String validateUserUri = "http://localhost:9806/api/v1/user/verify";

		ResponseEntity<LoginResponseDto> loginResponse = restTemplate.postForEntity(validateUserUri,
				new HttpEntity<LoginRequestDto>(logRequest), LoginResponseDto.class);

		if (HttpStatus.OK != loginResponse.getStatusCode() || !loginResponse.hasBody()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		LoginResponseDto responseDto = loginResponse.getBody();
		if (Objects.isNull(responseDto) || (StringUtils.isBlank(responseDto.getUsername()))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		String token = jwtUtils.generateJwtToken(responseDto.getUsername());
		String refToken = jwtUtils.generateRefreshToken(logRequest.getUsername());

		return ResponseEntity.ok(new AuthTokenDto(logRequest.getUsername(), token, refToken, LocalDateTime.now()));

	}

	@Override
	public ResponseEntity<LoginResponseDto> verifyToken(AuthTokenDto tokenRequest) {
		LoginResponseDto login = jwtUtils.validateJwtToken(tokenRequest.getToken());
		return ResponseEntity.ok(null);
	}

}
