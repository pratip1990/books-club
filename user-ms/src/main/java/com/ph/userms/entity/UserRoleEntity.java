/**
 * 
 */
package com.ph.userms.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles", schema = "schema_user")
@EntityListeners(AuditingEntityListener.class)
public class UserRoleEntity {

	@EmbeddedId
	private UserRolesId id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name = "role_id")
	private RoleEntity role;

}