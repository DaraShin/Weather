package com.shinkevich.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.shinkevich.weather.controller.WeatherController;
import com.shinkevich.weather.controller.response.AverageWeatherResponse;
import com.shinkevich.weather.controller.response.Location;
import com.shinkevich.weather.controller.response.WeatherResponse;
import com.shinkevich.weather.database.entity.Weather;
import com.shinkevich.weather.exception.NoDataFoundException;
import com.shinkevich.weather.exceptionhandler.RestExceptionHandler;
import com.shinkevich.weather.service.WeatherService;
import com.shinkevich.weather.exception.NoDataFoundException;
import com.shinkevich.weather.exception.DateFormatException;

public class WeatherControllerTests {
	private MockMvc mockMvc;

	@Mock
	private WeatherService weatherService;

	@InjectMocks
	private WeatherController weatherController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(weatherController).setControllerAdvice(new RestExceptionHandler())
				.build();
	}

	@Test
	void getLatestWeatherSuccessTest() throws Exception {
		WeatherResponse weatherResponse = new WeatherResponse();
		weatherResponse.setTemperatureC(5);
		weatherResponse.setWindMph(3.8);
		weatherResponse.setPressureMb(1005);
		weatherResponse.setHumidity(85);
		weatherResponse.setCondition("Freezing fog");
		weatherResponse.setLocation(new Location(53.9, 27.57));

		when(weatherService.getLatestWeather()).thenReturn(weatherResponse);

		ResponseEntity<WeatherResponse> response = weatherController.getLatestWeather();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(weatherResponse, response.getBody());

		verify(weatherService, times(1)).getLatestWeather();
	}

	@Test
	void getLatestWeatherNoDataTest() throws Exception {
		String exceptionMessage = "No weather information found";
		when(weatherService.getLatestWeather()).thenThrow(new NoDataFoundException(exceptionMessage));
		MvcResult result =  mockMvc
			.perform(get("/current-weather"))
			.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
			.andReturn();
		
		assertEquals(result.getResponse().getContentAsString(), exceptionMessage);
		verify(weatherService, times(1)).getLatestWeather();
	}
	
	@Test 
	void getAverageWeatherForPeriodSuccessTest() throws ParseException {
		AverageWeatherResponse averageWeatherResponse = new AverageWeatherResponse();
		averageWeatherResponse.setAverageTemperatureC(3.5);
		averageWeatherResponse.setAverageWindMph(3.0);
		averageWeatherResponse.setAveragePressureMb(1005.0);
		averageWeatherResponse.setAverageHumidity(81.5);
		averageWeatherResponse.setLocation(new Location(53.9, 27.57));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String from = "01-12-2023";
		String to = "02-12-2023";
		
		when(weatherService.getAverageWeatherForPeriod(dateFormat.parse(from), dateFormat.parse(to))).thenReturn(averageWeatherResponse);
		
		ResponseEntity<AverageWeatherResponse> response = weatherController.getAverageWeatherForPeriod(from ,to);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(averageWeatherResponse, response.getBody());
	}
	
	@Test
	void getAverageWeatherForPeriodParseExceptionTest() throws Exception {
		String exceptionMessage = "Date must have format dd-mm-yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String from = "01-12-2023";
		String to = "02-12-2023";
		
		when(weatherService.getAverageWeatherForPeriod(dateFormat.parse(from), dateFormat.parse(to))).thenThrow(new DateFormatException(exceptionMessage));
		
		MvcResult result =  mockMvc
			.perform(get("/average-weather")
					.param("from", from)
					.param("to", to))
			.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
			.andReturn();
		
		assertEquals(result.getResponse().getContentAsString(), exceptionMessage);
	}
}
