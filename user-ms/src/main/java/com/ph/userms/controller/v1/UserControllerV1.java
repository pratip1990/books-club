/**
 * 
 */
package com.ph.userms.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;
import com.ph.coredtos.dto.UserDTO;
import com.ph.coredtos.dto.UserResponseDto;
import com.ph.userms.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


/**
 * 
 */
@RestController
@RequestMapping("/user/api/v1")
@Tag(name = "User Management - V1")
public class UserControllerV1 {

    private final UserService userService;

    @Autowired
    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all Users", description = "Retrieve a list of all Users")
    @ApiResponse(responseCode = "200", description = "Successful response")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> products = userService.getAllUsers();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get User by ID", description = "Retrieve a User by its ID")
    @ApiResponse(responseCode = "200", description = "Successful response")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Create a new User", description = "Create a new User to the system")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a User", description = "Update an existing User in the system")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete a User", description = "Delete a User from the system")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @Operation(summary = "Verify a User", description = "Verify a User from the system")
    @ApiResponse(responseCode = "204", description = "User Verified successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PostMapping("/verify")
    public ResponseEntity<LoginResponseDto> deleteUser(@RequestBody LoginRequestDto loginRequestDto) {
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