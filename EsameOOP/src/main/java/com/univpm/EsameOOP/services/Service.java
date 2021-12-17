package com.univpm.EsameOOP.services;

import java.io.IOException;

import org.json.simple.JSONObject;

public interface Service {

	public abstract JSONObject getGeneralWeather(String city);
	public abstract String UNIXConverter(long time);
	public abstract JSONObject getVisibility(String city);
	public abstract JSONObject getWind(String city);
	public abstract JSONObject getVisibilityAndWind(String city);
	public abstract String save(String city) throws IOException;
	public abstract String savehour(String city);
}
