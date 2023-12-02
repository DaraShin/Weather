package com.shinkevich.weather.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shinkevich.weather.controller.response.AverageWeatherResponse;
import com.shinkevich.weather.database.entity.Weather;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Date> {
	@Query("SELECT w FROM Weather w WHERE w.recordTime = (SELECT max(w2.recordTime) FROM Weather w2)")
	public Weather getLatestWeather();
	
	@Query("SELECT new com.shinkevich.weather.controller.response.AverageWeatherResponse("
			+ "avg(w.temperatureC), "
			+ "avg(w.windMph),"
			+ "avg(w.pressureMb),"
			+ "avg(w.humidity),"
			+ "new com.shinkevich.weather.controller.response.Location(avg(w.latitude), avg(w.longitude))"
			+ ") FROM Weather w WHERE w.recordTime BETWEEN :from AND :to")
	public AverageWeatherResponse getAverageWeather(Date from , Date to);
}
