package com.univpm.EsameOOP.services;

import org.springframework.format.Printer;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.*;
//import java.sql.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceImplementation implements com.univpm.EsameOOP.services.Service {

	private String ApiKey="32469d04fb266e14e1d1f0d15a2599e8";
	public JSONObject getGeneralWeather(String city)
	{
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + ApiKey;

		RestTemplate rt = new RestTemplate();

		obj =new JSONObject(rt.getForObject(url, JSONObject.class));

		return obj;
	}
	public String UNIXConverter(int time)
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
		double gust;
		if(obj2.get("gust")==null) {
			gust=0;
		}
		else gust=(double) obj2.get("gust");
		int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);
		obj3.put("Speed", speed);
		obj3.put("Degrees", degrees);
		obj3.put("Date", formattedTime);
		obj3.put("Gust", gust);
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
		double gust;
		if(obj2.get("gust")==null) {
			gust=0;
		}
		else gust=(double) obj2.get("gust");
		int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);
		int visibility=(int) obj.get("visibility");
		obj3.put("Speed", speed);
		obj3.put("Degrees", degrees);
		obj3.put("Gust", gust);
		obj3.put("Visibility", visibility);
		obj3.put("Date", formattedTime);
		return obj3;

	}

	public String save(String city) throws IOException
	{


		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());


		String nFile=city+"."+today+".txt";
		String path=System.getProperty("user.dir")+nFile;


		JSONObject CITY=getVisibilityAndWind( city);
		

		try{

			File file_out=new File(nFile);

			if(file_out.exists())
			{
				FileOutputStream existing_file= new FileOutputStream(nFile,true);
				PrintWriter scrivi= new PrintWriter(existing_file);
				scrivi.append("Speed : "+CITY.get("Speed")+"\n");
				scrivi.append("Degrees : "+CITY.get("Degrees")+"\n");
				scrivi.append("Gust : "+CITY.get("Gust")+"\n");
				scrivi.append("Visibility : "+CITY.get("Visibility")+"\n");
				scrivi.append("Data : "+CITY.get("Date")+"\n");
				scrivi.close();

			}
			else 
			{
				file_out.createNewFile();
				PrintWriter scrivi= new PrintWriter(file_out);
				scrivi.print(nFile+"\n");
				scrivi.append("Speed : "+CITY.get("Speed")+"\n");
				scrivi.append("Degrees : "+CITY.get("Degrees")+"\n");
				scrivi.append("Gust : "+CITY.get("Gust")+"\n");
				scrivi.append("Visibility : "+CITY.get("Visibility")+"\n");
				scrivi.append("Data : "+CITY.get("Date")+"\n");
				scrivi.close();

			}

		}catch (IOException e) {System.out.println("error");};


		return nFile;
	}
	public String savehour(String city)
	{
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new Runnable() 
		{
			@Override
			public void run() {


				try {
					save(city);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.HOURS);
		return "fatto";
	}
	public JSONObject readData(String fileName) throws IOException
	{
		String path=System.getProperty("user.dir")+"\\" + fileName+".txt";
		//String path="C:\\Users\\bordo\\Desktop\\Esame\\EsameOOP\\"+fileName+".txt";
		int cont=0;
		int cont2=1;
		String control="";

		JSONObject jsonObject=new JSONObject();
		JSONObject jsonObject3=new JSONObject();
		JSONArray jsonObject2=new JSONArray();
		jsonObject3.put("FileName", fileName+".txt");
		BufferedReader filebuf= new BufferedReader(new FileReader(path));
		String [] words;
		try {control=filebuf.readLine();

		while(control!=null)	
		{
			if(cont==0) {
				cont++;
			}
			else 
			{ 
				if(cont%5==1)  jsonObject=new JSONObject();

				words=control.split(" "); 

				if(cont%5==0)jsonObject.put(words[0], words[2]+" "+words[3]);
				else jsonObject.put(words[0], words[2]);

				if (cont>=5 && cont%5==0) {
					jsonObject2.add((cont/5)-1, jsonObject);
					cont2++;
				}
				cont++;
			}
			control=filebuf.readLine();
		}
		}catch(IOException e)
		{
			System.out.println(e);
		}
		jsonObject3.put("Predictions", jsonObject2);
		return jsonObject3;	
	}
}
