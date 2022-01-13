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
import com.univpm.EsameOOP.model.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.client.RestTemplate;

/**
 * Classe che implementa Service andando a definire i metodi usati nel controller
 * @author Leonardo Bordoni
 * @author Samuele di Summa
 */
@Service
public class ServiceImplementation implements com.univpm.EsameOOP.services.Service {

	/**
	 * Key usata per fare le chiamte ad OpenWeather.L'Api usata si chiama Current, non prevedeva 
	 * previsoni meteo future ma solo attuali.
	 */
	private String ApiKey="32469d04fb266e14e1d1f0d15a2599e8";
	
	/**
	 * Metodo che ritorna un JSONObject contenente tutte le informazioni date da OpenWeather di una determinata città
	 * @param city il nome della città
	 * @return un JSONObject contenente le informazioni descritte sopra
	 */
	public JSONObject getGeneralWeather(String city) 
	{
		
		JSONObject obj;
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + ApiKey;

		RestTemplate rt = new RestTemplate();

		obj =new JSONObject(rt.getForObject(url, JSONObject.class));

		return obj;
	}
	/**
	 * Meotodo che converte le date dal formato UNIX a quello standard
	 * @param time la data UNIX che si vuole convertire
	 * @return la data nel formato standard
	 */
	public String UNIXConverter(int time)
	{
		Date date = new Date(time*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	/**
	 * Metodo che serve a ritornare solo le informazioni riguardanti la visibilità in una città
	 * @param city nome della città
	 * @return un JSONObject contente solo la data della previsione e il valore della visibilità
	 */
	public JSONObject getVisibility(String city) 
	{
		JSONObject obj=getGeneralWeather(city) ;
		int visibility=(int) obj.get("visibility");

		int time=(int) obj.get("dt");
		String formattedTime=UNIXConverter(time);
		JSONObject obj2=new JSONObject();
		obj2.put("Visibility", visibility);
		obj2.put("Date", formattedTime);
		return obj2;
	}
	/**
	 * Metodo che serve a ritornare solo le informazioni riguardanti il vento in una città
	 * @param city nome della città
	 * @return un JSONObject contente solo la data della previsione e i valori del vento
	 */
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
	/**
	 * Metodo che serve a generare un oggetto di tipo City con le informazioni di OpenWeather
	 * @param city il nome della città
	 * @return l'oggetto City contenente le informazioni
	 */
	public City createCity(String city)
	{
		JSONObject obj=getGeneralWeather(city);
		LinkedHashMap map1=new LinkedHashMap<>();
		map1=(LinkedHashMap) obj.get("coord");
		double lon=(double)map1.get("lon");
		double lat=(double)map1.get("lat");
		Coordinates coord=new Coordinates(lat,lon);
		LinkedHashMap map2=new LinkedHashMap<>();
		map2=(LinkedHashMap) obj.get("sys");
		String country=(String) map2.get("country");
		int id=(int) obj.get("id");
		City newCity=new City(coord,id,city,country);
		return newCity;
		
	}
	/**
	 * Metodo che serve a salvare in un oggetto City le informazioni riguardo la visibilità e il vento
	 * @param city il nome della città
	 * @return L'oggetto City corrispondente alla città con all'interno le informazioni riguardo vis. e vento
	 */
	public City getVisibilityAndWind(String city)
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
		Weather prediction =new Weather((float)degrees,(float)gust,(float)speed,(int)visibility,formattedTime);
		City cc=createCity(city);
		cc.getPredictions().add(prediction);
		return cc;

	}

	/**
	 * Metodo che salva su un file di testo la previsone meteorologica attuale di una città
	 * Ogni file viene salvato con questo nome: nomecittà.yyyy-mm-dd.txt
	 * @param city il nome della città
	 * @return il nome del file di testo 
	 */
	public String save(String city) throws IOException
	{


		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());


		String nFile=city+"."+today+".txt";
		String path=System.getProperty("user.dir")+nFile;


		City CITY=getVisibilityAndWind(city);
		
		
		try{

			File file_out=new File(nFile);

			if(file_out.exists())
			{
				FileOutputStream existing_file= new FileOutputStream(nFile,true);
				PrintWriter scrivi= new PrintWriter(existing_file);
				scrivi.append("Speed : "+CITY.getPredictions().get(0).getSpeed()+"\n");
				scrivi.append("Degrees : "+CITY.getPredictions().get(0).getDegrees()+"\n");
				scrivi.append("Gust : "+CITY.getPredictions().get(0).getGust()+"\n");
				scrivi.append("Visibility : "+CITY.getPredictions().get(0).getVisibilty()+"\n");
				scrivi.append("Data : "+CITY.getPredictions().get(0).getDate()+"\n");
				scrivi.close();

			}
			else 
			{
				file_out.createNewFile();
				PrintWriter scrivi= new PrintWriter(file_out);
				scrivi.print(nFile+"\n");
				scrivi.append("Speed : "+CITY.getPredictions().get(0).getSpeed()+"\n");
				scrivi.append("Degrees : "+CITY.getPredictions().get(0).getDegrees()+"\n");
				scrivi.append("Gust : "+CITY.getPredictions().get(0).getGust()+"\n");
				scrivi.append("Visibility : "+CITY.getPredictions().get(0).getVisibilty()+"\n");
				scrivi.append("Data : "+CITY.getPredictions().get(0).getDate()+"\n");
				scrivi.close();

			}

		}catch (IOException e) {System.out.println("error");};


		return nFile;
	}
	/**
	 * Metodo che ogni ora chiama il metodo save precedentemente descritto
	 * @param city il nome della città 
	 * @return Una stringa che ci informa che sta eseguendo i salvataggi
	 */
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
		}, 0, 5, TimeUnit.HOURS);
		return "Salvataggio in corso...";
	}
	/**
	 * Metodo che legge i dati da un file e li tramuta in un JSONObject
	 * @param fileName è il nome della città di cui si vuole ricercare il file
	 * @param day è la data ,scritta nel formato yyyy-mm-dd, di cui cui si vogliono le previsoni. 
	 */
	public JSONObject readData(String fileName, String day) throws IOException
	{
		String path=System.getProperty("user.dir")+"\\" + fileName+"."+day+".txt";
		int cont=0;
		int cont2=1;
		String control="";

		JSONObject jsonObject=new JSONObject();
		JSONObject jsonObject3=new JSONObject();
		JSONArray jsonObject2=new JSONArray();
		jsonObject3.put("FileName", fileName+"."+day+".txt");
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
