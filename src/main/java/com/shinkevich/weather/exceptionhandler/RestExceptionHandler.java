package com.shinkevich.weather.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shinkevich.weather.exception.DateFormatException;
import com.shinkevich.weather.exception.NoDataFoundException;
import com.shinkevich.weather.service.WeatherUpdateService;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(NoDataFoundException.class)
	private ResponseEntity<String> handleDataAccessException(NoDataFoundException ex, WebRequest request) {
		logger.warn("NoDataFoundException: " + ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DateFormatException.class)
	private ResponseEntity<String> handleDataAccessException(DateFormatException ex, WebRequest request) {
		logger.warn("DateFormatException: " + ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	private ResponseEntity<String> handleDataAccessException(Exception ex, WebRequest request) {
		logger.error("Exception: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
