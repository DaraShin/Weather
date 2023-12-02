package com.shinkevich.weather.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class AverageWeatherResponse {
	@Getter
	@Setter
	@JsonProperty("average_temperature_c")
	private Double averageTemperatureC;
	
	@Getter
	@Setter
	@JsonProperty("average_wind_mph")
	private Double averageWindMph;
	
	@Getter
	@Setter
	@JsonProperty("average_pressure_mb")
	private Double averagePressureMb;
	
	@Getter
	@Setter
	@JsonProperty("average_humidity")
	private Double averageHumidity;
	
	@Getter
	@Setter
	private Location location;
	
	public AverageWeatherResponse() {
		
	}

	public AverageWeatherResponse(
			Double averageTemperatureC, 
			Double averageWindMph, 
			Double averagePressureMb,
			Double averageHumidity,
			Location location) {
		this.averageTemperatureC = averageTemperatureC;
		this.averageWindMph = averageWindMph;
		this.averagePressureMb = averagePressureMb;
		this.averageHumidity = averageHumidity;
		this.location = location;
	}
	

}
