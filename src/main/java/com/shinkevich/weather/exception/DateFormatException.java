package com.shinkevich.weather.exception;

public class DateFormatException extends RuntimeException {
	private String message = "";
	
	public DateFormatException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
