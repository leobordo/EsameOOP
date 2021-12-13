package com.univpm.EsameOOP.services;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
//import java.sql.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;


public class ServiceImplementation implements Service {

	private String ApiKey="32469d04fb266e14e1d1f0d15a2599e8";
	public JSONObject getGeneralWeather(String city)
	{
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + ApiKey;
		
		RestTemplate rt = new RestTemplate();
		
		obj =new JSONObject(rt.getForObject(url, JSONObject.class));
		
		return obj;
	}
	public String UNIXConverter(long time)
	{
		Date date = new Date(time*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	public JSONObject getVisibility(String city)
	{
		JSONObject obj=getGeneralWeather(city);
		int visibility=(int) obj.get("visibility");
		long time=(long) obj.get("dt");
		String formattedTime=UNIXConverter(time);
		JSONObject obj2=new JSONObject();
		obj2.put("Visibility", visibility);
		obj2.put("Date", formattedTime);
		return obj2;
	}
}
