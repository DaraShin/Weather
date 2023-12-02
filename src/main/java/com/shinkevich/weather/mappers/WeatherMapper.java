package com.shinkevich.weather.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.shinkevich.weather.controller.response.Location;
import com.shinkevich.weather.database.entity.Weather;
import com.shinkevich.weather.exception.ConvertionException;
import com.shinkevich.weather.weatherapi.response.WeatherResponse;

public class WeatherMapper {
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

	public static Weather apiResponseToDbEntity(WeatherResponse weatherResponse) throws ConvertionException {
		Weather weather = new Weather();

		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		try {
			weather.setRecordTime(dateFormat.parse(weatherResponse.location.localtime));
		} catch (ParseException e) {
			throw new ConvertionException("failed to parse record time");
		}

		weather.setTemperatureC(weatherResponse.current.tempC);
		weather.setWindMph(weatherResponse.current.windMph);
		weather.setPressureMb(weatherResponse.current.pressureMb);
		weather.setHumidity(weatherResponse.current.humidity);
		weather.setCondition(weatherResponse.current.condition.text);
		weather.setLatitude(weatherResponse.location.lat);
		weather.setLongitude(weatherResponse.location.lon);

		return weather;
	}

	public static com.shinkevich.weather.controller.response.WeatherResponse dbEntityToResponse(Weather weather) {
		com.shinkevich.weather.controller.response.WeatherResponse weatherResponse = new com.shinkevich.weather.controller.response.WeatherResponse();
		weatherResponse.setTemperatureC(weather.getTemperatureC());
		weatherResponse.setWindMph(weather.getWindMph());
		weatherResponse.setPressureMb(weather.getPressureMb());
		weatherResponse.setHumidity(weather.getHumidity());
		weatherResponse.setCondition(weather.getCondition());
		Location location = new Location();
		location.setLatitude(weather.getLatitude());
		location.setLongitude(weather.getLongitude());
		weatherResponse.setLocation(location);
		return weatherResponse;
	}

}
