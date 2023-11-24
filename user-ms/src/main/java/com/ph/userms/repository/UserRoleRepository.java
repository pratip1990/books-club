/**
 * 
 */
package com.ph.userms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ph.userms.entity.UserEntity;
import com.ph.userms.entity.UserRoleEntity;
import com.ph.userms.entity.UserRolesId;

/**
 * 
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRolesId> {

	List<UserRoleEntity> findByUserId(Long id);


}
