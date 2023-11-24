/**
 * 
 */
package com.ph.authms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ph.authms.entity.UserEntity;

/**
 * 
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

}
