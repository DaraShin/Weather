package com.shinkevich.weather.exception;

public class NoDataFoundException extends RuntimeException {
	private String message = "";
	
	public NoDataFoundException() {}
	
	public NoDataFoundException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
