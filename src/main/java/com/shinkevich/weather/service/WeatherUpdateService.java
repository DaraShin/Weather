package com.shinkevich.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.shinkevich.weather.database.entity.Weather;
import com.shinkevich.weather.exception.ConvertionException;
import com.shinkevich.weather.exception.WeatherRequestException;
import com.shinkevich.weather.mappers.WeatherMapper;
import com.shinkevich.weather.repository.WeatherRepository;
import com.shinkevich.weather.weatherapi.WeatherApiService;

import reactor.core.publisher.Mono;

@Service
public class WeatherUpdateService {
	private static Logger logger = LoggerFactory.getLogger(WeatherUpdateService.class);

	private WeatherApiService weatherApiService;
	private WeatherRepository weatherRepository;

	public WeatherUpdateService(WeatherApiService weatherApiService, WeatherRepository weatherRepository) {
		this.weatherApiService = weatherApiService;
		this.weatherRepository = weatherRepository;
	}

	@Scheduled(fixedRateString = "${update_period}")
	public void updateWeather() {
		weatherApiService
		.getCurrentWeather()
		.onErrorResume(exc -> exc instanceof WeatherRequestException, exc -> {
			logger.error("Weather update failed: failed to request weather");
			return Mono.empty();
		}).subscribe(weatherResponse -> {
			Weather weather;
			try {
				weather = WeatherMapper.apiResponseToDbEntity(weatherResponse);
				weatherRepository.save(weather);
			} catch (ConvertionException e) {
				logger.error("Weather update failed: " + e.getMessage());
			}catch (DataAccessException e) {
				logger.error("Weather update failed: failed to save weather to local database");
			}
			catch (Exception e) {
				logger.error("Weather update failed");
			}
		});

	}
}
