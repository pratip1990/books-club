/**
 * 
 */
package com.ph.userms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;
import com.ph.coredtos.dto.UserDTO;

/**
 * 
 */
public interface UserService {

    UserDTO getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

	List<UserDTO> getAllUsers();

	ResponseEntity<LoginResponseDto> verifyUserLogin(LoginRequestDto loginRequestDto);
}