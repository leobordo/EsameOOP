package com.univpm.EsameOOP.services;

import org.json.simple.JSONObject;

public interface Service {

	public abstract JSONObject getGeneralWeather(String city);
	public String UNIXConverter(long time);
	public JSONObject getVisibility(String city);
}
