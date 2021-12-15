package com.univpm.EsameOOP.services;

import com.univpm.EsameOOP.model.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
//import java.sql.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
@Service
public class ServiceImplementation implements  com.univpm.EsameOOP.services.Service {

	private String ApiKey="32469d04fb266e14e1d1f0d15a2599e8";
	public JSONObject getGeneralWeather(String city)
	{

		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + ApiKey;
		
		RestTemplate rt = new RestTemplate();
		
		JSONObject obj =new JSONObject(rt.getForObject(url, JSONObject.class));
		
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
		String country = (String) obj.get("country");
	
		int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);
		JSONObject obj2=new JSONObject();
		obj2.put("Visibility", visibility);
		
		obj2.put("Date", formattedTime);
		return obj2;
	}
	public JSONObject getWind(String city)
	{
		JSONObject obj=getGeneralWeather(city);
		LinkedHashMap obj2=new LinkedHashMap<>();
		obj2=(LinkedHashMap) obj.get("wind");
		JSONObject obj3=new JSONObject();

		double speed=(double) obj2.get("speed");
		int degrees=(int) obj2.get("deg");
		double gust=(double) obj2.get("gust");
		
        int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);

		obj3.put("Speed", speed);
		obj3.put("Degrees", degrees);
		obj3.put("Gust", gust);
		obj3.put("Date", formattedTime);
		return obj3;
		
	}
	
	public JSONObject getVisibilityAndWind(String city)
	{
		JSONObject obj=getGeneralWeather(city);
		LinkedHashMap obj2=new LinkedHashMap<>();
		obj2=(LinkedHashMap) obj.get("wind");
		JSONObject obj3=new JSONObject();

		double speed=(double) obj2.get("speed");
		int degrees=(int) obj2.get("deg");
		double gust=(double) obj2.get("gust");
		
        int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);

		int visibility=(int)obj.get("visibility");
		obj3.put("Speed", speed);
		obj3.put("Degrees", degrees);
		obj3.put("Gust", gust);
		obj3.put("Visibility",visibility);
		obj3.put("Date", formattedTime);
		return obj3;
		
	}
	
	
	
}
