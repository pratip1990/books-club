package com.ph.gatewayms.exception;

public class InvalidException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException(String msg){
		super(msg);
	}
	
	public InvalidException(String msg, RuntimeException exp){
		super(msg,exp);
	}
}
