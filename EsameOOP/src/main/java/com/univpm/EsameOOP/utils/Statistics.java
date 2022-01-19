package com.univpm.EsameOOP.utils;
import  com.univpm.EsameOOP.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.univpm.EsameOOP.exception.*;
import  com.univpm.EsameOOP.model.*;
import com.univpm.EsameOOP.services.ServiceImplementation;


/**
 * Classe che sviluppa le statistiche dei dati presi in esame
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class Statistics {

	/**
	 * Metodo che esegue le statistiche di vento e raffiche su un intera giornata. 
	 * Il metodo calcola velocità del vento max, min e media; velocità delle raffiche max,min e media
	 * @param cityname nome della città di cui si vogliono le statistiche
	 * @param day giorno di cui si vogliono le statistiche
	 * @return un JSONObject contenente le statistiche sopra descritte
	 */
	public JSONObject dailyStats(String cityname, String day)
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
			obj = s.readData(cityname, day);//il try serve a obj e basta
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
		objreturn.put("raffica media del vento ",gust_av);
		objreturn.put("velocità massima del vento ",speed_max);
		objreturn.put("velocità minima del vento ",speed_min);
		objreturn.put("raffica massima del vento ",gust_max);
		objreturn.put("raffica minima del vento ",gust_min);

		return objreturn;
	}
	

	/**
	 * Metodo che calcola le statistiche di vento e raffiche su una certa fascia oraria.
	 * Il metodo calcola velocità del vento max, min e media; velocità delle raffiche max,min e media
	 * @param cityname nome della città di cui si vogliono le statistiche
	 * @param day giorno di cui si vogliono le statistiche
	 * @param period fascia oraria di cui si vogliono le statistiche
	 * @return un JSONObject contenente le statistiche sopra descritte
	 */
	public JSONObject dailyStatsPlus(String cityname, String day,String period)
	{
		ServiceImplementation s= new ServiceImplementation();
		
		
		double hourI=Double.valueOf(period.substring(0, 2));
		Double hourF=Double.valueOf(period.substring(3));
			


		int i=(int)hourI;

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
			obj = s.readData(cityname, day);//il try serve a obj e basta
			obj2=(JSONArray) obj.get("Predictions");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		while(i<hourF) 
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
		objreturn.put("raffica media del vento ",gust_av);
		objreturn.put("velocità massima del vento ",speed_max);
		objreturn.put("velocità minima del vento ",speed_min);
		objreturn.put("raffica massima del vento ",gust_max);
		objreturn.put("raffica minima del vento ",gust_min);
		return objreturn;
	}





}