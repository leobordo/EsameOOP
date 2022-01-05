package com.univpm.EsameOOP.utils;
import  com.univpm.EsameOOP.*;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import  com.univpm.EsameOOP.model.*;
import com.univpm.EsameOOP.services.ServiceImplementation;


public class Statistics {
	
	public JSONObject today(String cityname)
	{
			ServiceImplementation s= new ServiceImplementation();
		
			int i=0;
			double speed_av=0;
			double gust_av=0;
			double speed_max=0;
			double gust_max=0;
			double speed_min=10000;//impossibile che raggiunga questo valore
			double gust_min=10000;//impossibile che raggiunga questo valore
			double sptemp;//var di appoggio
			double gutemp;//var di appoggio
			JSONObject obj=null;
			JSONArray obj2=null;
		try {
				obj = s.readData(cityname);//il try serve a obj e basta
				obj2=(JSONArray) obj.get("Predictions");
			} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		while(obj2.size()>i) 
					{
						JSONObject obj4=(JSONObject)obj2.get(i);
						
						sptemp=Double.valueOf((String)obj4.get("Speed"));//valueOf casta una var. string in Double,(String) casta obj4 da Object in string
						speed_av+=sptemp;
						gutemp=Double.valueOf((String)obj4.get("Gust"));
						gust_av+=gutemp;
						if(sptemp>speed_max)speed_max=sptemp;
						if(gutemp>gust_max)gust_max=gutemp;
						if(sptemp<speed_min)speed_min=sptemp;
						if(gutemp<gust_min)gust_min=gutemp;
						i++;
					}
				speed_av=speed_av/i;
				gust_av=gust_av/i;
		
		
		JSONObject objreturn=new JSONObject();
		objreturn.put("velocità media del vento ",speed_av);
		objreturn.put("raffica media  del vento ",gust_av);
		objreturn.put("velocità massima  del vento ",speed_max);
		objreturn.put("velocità minima  del vento ",speed_min);
		objreturn.put("raffica massima  del vento ",gust_max);
		objreturn.put("raffica minim  del vento ",gust_min);
	
	return objreturn;
	}
	


}