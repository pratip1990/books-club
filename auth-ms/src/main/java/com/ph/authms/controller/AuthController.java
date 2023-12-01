/**
 * 
 */
package com.ph.authms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ph.authms.service.AuthService;
import com.ph.coredtos.dto.AuthTokenDto;
import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;



/**
 * 
 */

@RestController
@RequestMapping("/auth/api/v1/")
public class AuthController {

	private final AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(value = "/tkn", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthTokenDto> tokenGeneration(@RequestBody LoginRequestDto logRequest){
		return  authService.generateToken(logRequest);
	}
	
	@PostMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponseDto> verifyToken(AuthTokenDto tokenRequest){
		return  authService.verifyToken(tokenRequest);
	}
}
