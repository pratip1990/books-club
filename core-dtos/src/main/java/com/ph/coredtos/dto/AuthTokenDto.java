/**
 * 
 */
package com.ph.coredtos.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3085384940869889562L;
	
	private String username;
	private String token;
	private String refToken;
	private LocalDateTime currentTimestamp;
	

}
