package com.shinkevich.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shinkevich.weather.controller.response.AverageWeatherResponse;
import com.shinkevich.weather.controller.response.Location;
import com.shinkevich.weather.controller.response.WeatherResponse;
import com.shinkevich.weather.database.entity.Weather;
import com.shinkevich.weather.exception.NoDataFoundException;
import com.shinkevich.weather.repository.WeatherRepository;
import com.shinkevich.weather.service.WeatherService;

public class WeatherServiceTest {
	@Mock
	private WeatherRepository weatherRepository;

	@InjectMocks
	WeatherService weatherService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getLatestWeatherTest() {
		Weather mockWeather = new Weather();
		mockWeather.setTemperatureC(5);
		mockWeather.setWindMph(3.8);
		mockWeather.setPressureMb(1005);
		mockWeather.setHumidity(85);
		mockWeather.setCondition("Freezing fog");
		mockWeather.setLatitude(53.9);
		mockWeather.setLongitude(27.57);
		mockWeather.setRecordTime(new Date());

		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setTemperatureC(5);
		weatherResponse.setWindMph(3.8);
		weatherResponse.setPressureMb(1005);
		weatherResponse.setHumidity(85);
		weatherResponse.setCondition("Freezing fog");
		weatherResponse.setLocation(new Location(53.9, 27.57));

		when(weatherRepository.getLatestWeather()).thenReturn(mockWeather);

		WeatherResponse result = weatherService.getLatestWeather();

		verify(weatherRepository, times(1)).getLatestWeather();

		assertEquals(5, result.getTemperatureC());
		assertEquals(3.8, result.getWindMph());
		assertEquals(1005, result.getPressureMb());
		assertEquals(85, result.getHumidity());
		assertEquals("Freezing fog", result.getCondition());
		assertEquals(53.9, result.getLocation().getLatitude());
		assertEquals(27.57, result.getLocation().getLongitude());
	}

	@Test
	public void getLatestWeatherNoDataTest() {
		when(weatherRepository.getLatestWeather()).thenReturn(null);
		assertThrows(NoDataFoundException.class, () -> {
			weatherService.getLatestWeather();
			});
		}

	@Test
	public void getAverageWeatherForPeriodTest() {
		AverageWeatherResponse mockResponse = new AverageWeatherResponse();
		mockResponse.setAverageTemperatureC(1.0);
		mockResponse.setAverageWindMph(1.0);
		mockResponse.setAveragePressureMb(1.0);
		mockResponse.setAverageHumidity(1.0);
		mockResponse.setLocation(new Location(1.0, 1.0));

		Date from = new Date();
		Date to = new Date();

		when(weatherRepository.getAverageWeather(from, to)).thenReturn(mockResponse);

		AverageWeatherResponse result = weatherService.getAverageWeatherForPeriod(from, to);

		verify(weatherRepository).getAverageWeather(from, to);
		assertEquals(result.getAverageTemperatureC(), 1.0);
		assertEquals(result.getAverageWindMph(), 1.0);
		assertEquals(result.getAveragePressureMb(), 1.0);
		assertEquals(result.getAverageHumidity(), 1.0);
		assertEquals(result.getLocation().getLatitude(), 1.0);
		assertEquals(result.getLocation().getLongitude(), 1.0);
	}
}
