package com.ph.gatewayms.exception;

public class TokenException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenException(String msg){
		super(msg);
	}
	
	public TokenException(String msg, RuntimeException exp){
		super(msg,exp);
	}
}
