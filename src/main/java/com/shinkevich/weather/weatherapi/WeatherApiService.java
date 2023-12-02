package com.shinkevich.weather.weatherapi;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.shinkevich.weather.exception.WeatherRequestException;
import com.shinkevich.weather.weatherapi.response.WeatherResponse;

import reactor.core.publisher.Mono;

@Service
public class WeatherApiService {
	private static final String BASE_URL = "https://weatherapi-com.p.rapidapi.com/current.json";
	private static final String HOST_HEADER = "X-RapidAPI-Host";
	private static final String HOST = "weatherapi-com.p.rapidapi.com";
	private static final String API_KEY_HEADER = "X-RapidAPI-Key";
	private static final String API_KEY = "50f3b4aaa2msh4b371c2497a1a0cp19bf72jsnc03c8173b111";


	public Mono<WeatherResponse> getCurrentWeather()  {
		return WebClient.create(BASE_URL)
				.get()
				.uri(uriBuilder -> 
					uriBuilder
						.queryParam("q", "Minsk")
						.build()
				)
				.accept(MediaType.APPLICATION_JSON)
				.header(HOST_HEADER, HOST)
				.header(API_KEY_HEADER, API_KEY)
				.retrieve()
				.bodyToMono(WeatherResponse.class)
				.onErrorMap(exc ->{
					return new WeatherRequestException();
				});
	}

}
