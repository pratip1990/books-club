/**
 * 
 */
package com.ph.userms.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;
import com.ph.coredtos.dto.UserResponseDto;
import com.ph.userms.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * 
 */
@RestController
@RequestMapping("/user/api/v1/auth")
@Tag(name = "User Management for Auth - V1")
public class UserAuthControllerV1 {

    private final UserService userService;

    @Autowired
    public UserAuthControllerV1(UserService userService) {
        this.userService = userService;
    }
    
    @Operation(summary = "Verify a User", description = "Verify a User from the system")
    @ApiResponse(responseCode = "204", description = "User Verified successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PostMapping("/verify")
    public ResponseEntity<LoginResponseDto> verifyUserAuthService(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.verifyUserLogin(loginRequestDto);
    }
    
    @Operation(summary = "Get Details of User by username", description = "Get Details of User by username")
    @ApiResponse(responseCode = "204", description = "User Verified successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping(value = "/get-user-username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> deleteUser(@RequestParam("username") String username) {
        return userService.getUserDetailsByUsername(username);
    }
}