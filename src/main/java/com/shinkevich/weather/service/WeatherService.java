package com.shinkevich.weather.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.shinkevich.weather.controller.response.AverageWeatherResponse;
import com.shinkevich.weather.controller.response.WeatherResponse;
import com.shinkevich.weather.database.entity.Weather;
import com.shinkevich.weather.exception.NoDataFoundException;
import com.shinkevich.weather.mappers.WeatherMapper;
import com.shinkevich.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	private WeatherRepository weatherRepository;

	public WeatherService(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	public WeatherResponse getLatestWeather() throws NoDataFoundException {
		Weather weather = weatherRepository.getLatestWeather();
		if (weather == null) {
			throw new NoDataFoundException("No weather information found");
		}
		return WeatherMapper.dbEntityToResponse(weather);
	}

	public AverageWeatherResponse getAverageWeatherForPeriod(Date from, Date to) {
		AverageWeatherResponse averageWeather = weatherRepository.getAverageWeather(from, to);
		if(averageWeather.getAverageTemperatureC() == null) {
			throw new NoDataFoundException("No weather information for period");
		}
		return averageWeather;
	}

}
