package com.shinkevich.weather.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shinkevich.weather.controller.response.AverageWeatherResponse;
import com.shinkevich.weather.controller.response.WeatherResponse;
import com.shinkevich.weather.exception.DateFormatException;
import com.shinkevich.weather.repository.WeatherRepository;
import com.shinkevich.weather.service.WeatherService;

@RestController
public class WeatherController {
	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping("/current-weather")
	public ResponseEntity<WeatherResponse> getLatestWeather() {
		WeatherResponse weatherResponse = weatherService.getLatestWeather();
		return ResponseEntity.ok(weatherResponse);
	}

	@GetMapping("/average-weather")
	public ResponseEntity<AverageWeatherResponse> getAverageWeatherForPeriod(@RequestParam String from,
			@RequestParam String to) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
			AverageWeatherResponse avgWeather = weatherService.getAverageWeatherForPeriod(dateFormat.parse(from),
					dateFormat.parse(to));
			return ResponseEntity.ok(avgWeather);
		} catch (ParseException e) {
			throw new DateFormatException("Date must have format dd-mm-yyyy");
		}
	}
}
