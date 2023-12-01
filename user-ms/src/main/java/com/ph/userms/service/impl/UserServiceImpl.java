/**
 * 
 */
package com.ph.userms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ph.coredtos.dto.LoginRequestDto;
import com.ph.coredtos.dto.LoginResponseDto;
import com.ph.coredtos.dto.UserDTO;
import com.ph.userms.entity.UserEntity;
import com.ph.userms.entity.UserRoleEntity;
import com.ph.userms.exception.NotFoundException;
import com.ph.userms.repository.UserRepository;
import com.ph.userms.repository.UserRoleRepository;
import com.ph.userms.service.UserService;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setCreatedBy(user.getUsername());
        user.setUpdatedBy(user.getUsername());
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
    	 UserEntity user = userRepository.findById(id)
                 .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    	 UserEntity savedUser = userRepository.save(user);
    	 return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserEntity> products = userRepository.findAll();
		return products.stream().map(a-> modelMapper.map(a, UserDTO.class)).toList();
	}

	@Override
	public ResponseEntity<LoginResponseDto> verifyUserLogin(LoginRequestDto loginRequest) {
		UserEntity user = userRepository.findByUsername(loginRequest.getUsername(), loginRequest.getPassword());
		if(Objects.isNull(user)) {
			return ResponseEntity.notFound().build();
		}
		List<UserRoleEntity> userRoles = userRoleRepository.findByUserId(user.getId());
		if(Objects.isNull(userRoles) || userRoles.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		LoginResponseDto loginResponseDto = new LoginResponseDto(user.getUsername(), userRoles.get(0).getRole().getName());
		return ResponseEntity.ok(loginResponseDto);
	}

}
