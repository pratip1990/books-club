/**
 * 
 */
package com.ph.authms.service;

import org.springframework.http.ResponseEntity;

import com.ph.coredtos.dto.AuthTokenDto;
import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;

/**
 * 
 */
public interface AuthService {

	ResponseEntity<AuthTokenDto> generateToken(LoginRequestDto logRequest);

	ResponseEntity<LoginResponseDto> verifyToken(AuthTokenDto tokenRequest);

}
