package com.shinkevich.weather.controller.response;

import lombok.Getter;
import lombok.Setter;

public class Location {

	@Getter
	@Setter
	private Double latitude;

	@Getter
	@Setter
	private Double longitude;

	public Location() {}

	public Location(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
