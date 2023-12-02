package com.shinkevich.weather.exception;

public class ConvertionException extends Exception {
	private String message = "";
	
	
	public ConvertionException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
