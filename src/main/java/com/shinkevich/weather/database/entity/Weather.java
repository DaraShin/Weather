package com.shinkevich.weather.database.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather")
public class Weather {
	@Id
	@Getter
	@Setter
	@Column(name = "record_time")
	private Date recordTime;
	
	@Getter
	@Setter
	@Column(name = "temperature_c")
	private double temperatureC;
	
	@Getter
	@Setter
	@Column(name = "wind_mph")
	private double windMph;
	
	@Getter
	@Setter
	@Column(name = "pressure_mb")
	private double pressureMb;
	
	@Getter
	@Setter
	private int humidity;
	
	@Getter
	@Setter
	private String condition;
	
	@Getter
	@Setter
	private double latitude;
	
	@Getter
	@Setter
	private double longitude;
}
