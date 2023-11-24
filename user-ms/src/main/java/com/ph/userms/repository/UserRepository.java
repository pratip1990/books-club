/**
 * 
 */
package com.ph.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ph.userms.entity.UserEntity;

/**
 * 
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("SELECT user FROM UserEntity user WHERE (user.username = :username OR user.email = :username OR user.phone = :username) and password = :password")
	UserEntity findByUsername(String username, String password);

}
