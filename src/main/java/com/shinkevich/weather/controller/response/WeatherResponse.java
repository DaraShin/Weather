package com.shinkevich.weather.controller.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class WeatherResponse {
	
	@Getter
	@Setter
	@JsonProperty("temperature_c")
	private double temperatureC;
	
	@Getter
	@Setter
	@JsonProperty("wind_mph")
	private double windMph;
	
	@Getter
	@Setter
	@JsonProperty("pressure_mb")
	private double pressureMb;
	
	@Getter
	@Setter
	private int humidity;
	
	@Getter
	@Setter
	private String condition;
	
	@Getter
	@Setter
	private Location location;
}
