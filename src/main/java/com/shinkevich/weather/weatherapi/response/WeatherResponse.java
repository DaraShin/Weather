package com.shinkevich.weather.weatherapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
	public Location location;
    public Current current;
    
    public static class Location{
	    public String name;
	    public String region;
	    public String country;
	    public double lat;
	    public double lon;
	    
	    @JsonProperty("tz_id")
	    public String tzId;
	    
	    @JsonProperty("localtime_epoch")
	    public int localtimeEpoch;
	    
	    public String localtime;
	}
    
    public static class Current{
    	@JsonProperty("last_updated_epoch")
	    public int lastUpdatedEpoch;
    	
    	@JsonProperty("last_updated")
	    public String lastUpdated;
    	
	    @JsonProperty("temp_c")
	    public double tempC;
	    
	    @JsonProperty("temp_f")
	    public double tempF;
	    
	    @JsonProperty("is_day")
	    public int isDay;
	    
	    public Condition condition;
	    
	    @JsonProperty("wind_mph")
	    public double windMph;
	    
	    @JsonProperty("wind_kph")
	    public double windKph;
	    
	    @JsonProperty("wind_degree")
	    public int windDegree;
	    
	    @JsonProperty("wind_dir")
	    public String windDir;
	    
	    @JsonProperty("pressure_mb")
	    public double pressureMb;
	    
	    @JsonProperty("pressure_in")
	    public double pressureIn;
	    
	    @JsonProperty("precip_mm")
	    public double precipMm;
	    
	    @JsonProperty("precip_in")
	    public double precipIn;
	    
	    public int humidity;
	    public int cloud;
	    
	    @JsonProperty("feelslike_c")
	    public double feelslikeC;
	    
	    @JsonProperty("feelslike_f")
	    public double feelslikeF;
	    
	    @JsonProperty("vis_km")
	    public double visKm;
	    
	    @JsonProperty("vis_miles")
	    public double visMiles;
	    public double uv;
	    
	    @JsonProperty("gust_mph")
	    public double gustMph;
	    
	    @JsonProperty("gust_kph")
	    public double gustKph;
	}
    
    public static class Condition{
	    public String text;
	    public String icon;
	    public int code;
	}
	
	
}
