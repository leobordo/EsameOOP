package com.univpm.EsameOOP.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import com.univpm.EsameOOP.exception.HourErrorException;
import com.univpm.EsameOOP.exception.CityErrorException;
import com.univpm.EsameOOP.exception.DayErrorException;
/**
 * Classe che filtra le statistiche su base settimanale,giornaliera,oraria
 * E' anche possibile filtrare le statistiche di più giorni sulla stessa fascia oraria
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class Filters {

	/**
	 * Classe che esegue i filtraggi a seconda delle esigenze	
	 * @param cityname nome della città
	 * @param dayI giorno inziale della fascia in cui si vogliono le statistiche.
	 * @param dayF giorno finale della fascia in cui si vogliono le statistiche. Se le si vogliono su un giorno solo non 
	 * è necessario dayF
	 * @param period fascia oraria su cui si vogliono le statistiche.Se non si vuole fare un filtraggio su fascia oraria non 
	 * è necessario period
	 * @return un JSONObject contenente le statistiche desiderate.
	 * @throws HourErrorException viene lanciata nei casi in cui ci sono errori sulle orario
	 * @throws CityErrorException viene lanciata nei casi in cui ci sono errori sul nome della città
	 * @throws DayErrorException vinee lanciata nei casi in cui ci sono errori sui giorni
	 * @throws FileNotFoundException viene lanciata nel caso in cui non esiste il file da cui si vogliono fare le statistiche
	 */
	public JSONObject choice(String cityname, String dayI ,String dayF, String period) throws HourErrorException ,CityErrorException, DayErrorException, FileNotFoundException
		{
			ErrorCity(cityname);
			if(!(dayF==null)&&dayF.isEmpty()) dayF=null;

			if(!(period==null)&&period.isEmpty()) period=null;

			ErrorDays(cityname,dayI,dayF);
			if(!(period==null))ErrorHour(period);
			
			
			JSONObject toReturn=new JSONObject();
			Statistics s=new Statistics();
			if(dayF==null && period==null) {
				
				toReturn=s.dailyStats(cityname, dayI);
			}else
				{if(dayF==null)
					if(period==null)toReturn=s.dailyStats(cityname, dayI);
					else
					{
						
						toReturn=s.dailyStatsPlus(cityname, dayI, period);
					}
						
					
				else 
				{
					JSONObject obj=new JSONObject();
					int i=Integer.valueOf(dayI.substring(8));
					int cont=0;
					double spAv=0;
					double spMax=0;
					double spMin=10000;
					double gustAv=0;
					double gustMax=0;
					double gustMin=10000;
					double sptemp;
					double gutemp;
					boolean ok=false;
					boolean ok2=false;
					
					
					if(dayF.substring(5, 7).equals(dayI.substring(5, 7))) //comparazione mesi
					{
						String MonthYear=dayI.substring(0, 8);
						while (i!=Integer.valueOf(dayF.substring(8)))
						{
							if(i==Integer.valueOf(dayI.substring(8))) obj=(period==null?s.dailyStats(cityname, dayI):s.dailyStatsPlus(cityname, dayI, period)); // controlla se i è il g iniziale
							else if(i==Integer.valueOf(dayF.substring(8))) obj=(period==null?s.dailyStats(cityname, dayF):s.dailyStatsPlus(cityname, dayF, period)); //controlla se i è il g finale
							else if(i<10) obj=(period==null?s.dailyStats(cityname, MonthYear+"0"+i):s.dailyStatsPlus(cityname, MonthYear+"0"+i, period));
							else  obj=(period==null?s.dailyStats(cityname, MonthYear+i):s.dailyStatsPlus(cityname, MonthYear+i, period));
							sptemp=(double) obj.get("velocità media del vento");
							gutemp=(double) obj.get("raffica media del vento");
							spAv+=sptemp;
							gustAv+=gutemp;
							sptemp=(double) obj.get("velocità massima del vento");
							gutemp=(double) obj.get("raffica massima del vento");
							if(sptemp>spMax)spMax=sptemp;
							if(gutemp>gustMax)gustMax=gutemp;
							sptemp=(double) obj.get("velocità minima del vento");
							gutemp=(double) obj.get("raffica minima del vento");
							if(sptemp<spMin)spMin=sptemp;
							if(gutemp<gustMin)gustMin=gutemp;
							i++;
							cont++;
						}
					}
					else {
						String Year=dayI.substring(0,5);
						int year=Integer.valueOf(dayI.substring(0,4));
						int month=Integer.valueOf(dayI.substring(5, 7));
						while (i!=Integer.valueOf(dayF.substring(8))+1) {
						if(month==2){
							if(i==28) ok=true;	
							else ok=false;
							if(i==Integer.valueOf(dayI.substring(8))) obj=(period==null?s.dailyStats(cityname, dayI):s.dailyStatsPlus(cityname, dayI, period));
								else {
									if(i==Integer.valueOf(dayF.substring(8))) obj=(period==null?s.dailyStats(cityname, dayF):s.dailyStatsPlus(cityname, dayF, period));
								else {
									if(i>22) obj=(period==null?s.dailyStats(cityname, Year+"02-"+i):s.dailyStatsPlus(cityname, Year+"02-"+i, period));
									else obj=(period==null?s.dailyStats(cityname, Year+"03-0"+i):s.dailyStatsPlus(cityname, Year+"03-0", period));
								}
								}
							}
					if(month==1||month==3||month==5||month==7||month==8||month==10)
					{
							if(i==31) ok=true;
							else {
								ok2=false;
								ok=false;
							}
							if(i==Integer.valueOf(dayI.substring(8))) obj=(period==null?s.dailyStats(cityname, dayI):s.dailyStatsPlus(cityname, dayI, period));
							else {if(i==Integer.valueOf(dayF.substring(8))) obj=(period==null?s.dailyStats(cityname, dayF):s.dailyStatsPlus(cityname, dayF, period));
							else {
								if(i>25) {
									obj=(period==null?s.dailyStats(cityname, Year+(month==10?month:"0"+month)+"-"+i):s.dailyStatsPlus(cityname, Year+(month==10?month:"0"+month), period));
											
								}
								else obj=(period==null?s.dailyStats(cityname, Year+(month==10?month:"0"+month)+"-0"+i):s.dailyStatsPlus(cityname, Year+(month==10?month:"0"+month)+"-0"+i, period));
							}
							}
					}
					if(month==4||month==6||month==9||month==11)
					{
						if(i==30) ok=true;	
						else ok=false;
						if(i==Integer.valueOf(dayI.substring(8))) obj=(period==null?s.dailyStats(cityname, dayI):s.dailyStatsPlus(cityname, dayI, period));
							else {if(i==Integer.valueOf(dayF.substring(8))) obj=(period==null?s.dailyStats(cityname, dayF):s.dailyStatsPlus(cityname, dayF, period));
							else {
								if(i>24) {
									obj=(period==null?s.dailyStats(cityname, Year+(month==11?month:"0"+month)+"-"+i):s.dailyStatsPlus(cityname, Year+(month==11?month:"0"+month)+"-"+i, period));
											
								}
								else obj=(period==null?s.dailyStats(cityname, Year+(month==10?month:"0"+month)+"-0"+i):s.dailyStatsPlus(cityname, Year+(month==10?month:"0"+month)+"-0"+i, period));
							}
							}
					}
					if(month==12)
					{
						if(i==31) ok2=true;
						else ok2=false;
						
							if(i==Integer.valueOf(dayI.substring(8))) obj=(period==null?s.dailyStats(cityname, dayI):s.dailyStatsPlus(cityname, dayI, period));
							else {if(i==Integer.valueOf(dayF.substring(8))) obj=(period==null?s.dailyStats(cityname, dayF):s.dailyStatsPlus(cityname, dayF, period));
							else {
								if(i>25) {
									obj=(period==null?s.dailyStats(cityname, Year+month+"-"+i):s.dailyStatsPlus(cityname, Year+month+"-"+i, period));
											
								}
								else obj=(period==null?s.dailyStats(cityname, year+"-"+month+"-0"+i):s.dailyStatsPlus(cityname, year+month+"-0"+i, period));
							}
							}		
					}
					sptemp=(double) obj.get("velocità media del vento");
					gutemp=(double) obj.get("raffica media del vento");
					spAv+=sptemp;
					gustAv+=gutemp;
					sptemp=(double) obj.get("velocità massima del vento");
					gutemp=(double) obj.get("raffica massima del vento");
					if(sptemp>spMax)spMax=sptemp;
					if(gutemp>gustMax)gustMax=gutemp;
					sptemp=(double) obj.get("velocità minima del vento");
					gutemp=(double) obj.get("raffica minima del vento");
					if(sptemp<spMin)spMin=sptemp;
					if(gutemp<gustMin)gustMin=gutemp;
					if(ok) {
						i=01;
						month++;
					}
					else {if(ok2){
							i=01;
							month=01;
							year++;}
						else i++;
					}
						cont++;
					
					}
					}
					spAv/=cont;
					gustAv/=cont;
					toReturn.put("velocità media del vento", spAv);
					toReturn.put("raffica media del vento", gustAv);
					toReturn.put("velcoità massima del vento", spMax);
					toReturn.put("velocità minima del vento", spMin);
					toReturn.put("raffica massima del vento", gustMax);
					toReturn.put("raffica minima del vento", gustMin);
	
				}
				}
			return toReturn;
		}
	/**
	 * Metodo che serve a lanciare l'eccezione CityErrorException
	 * Viene lanciata se: il nome della città non inzia con la maiuscola, la città non è tra quelle disponibili
	 * @param cityname nome della città
	 * @throws CityErrorException si riferisce ad errori sulla città
	 */
	public void ErrorCity(String cityname) throws CityErrorException
		{
	
			if(cityname.isEmpty()) throw new CityErrorException("inserire il nome della città");
			if(cityname.charAt(0)<65||cityname.charAt(0)>90) throw new CityErrorException("inserire il nome della città con la iniziale maiuscola");
			
			if(!(cityname.equals("Ancona")||cityname.equals("Roma"))) throw new CityErrorException("il nome della città non è in elenco (usa Roma o Ancona)");
			
		}
	/**
	 * Metodo che serve a lanciare le eccezioni DayErrorException e FileNotFoundException	
	 * La prima viene lanciata se: la data inserita è più vecchia di una settimana da oggi, la data non è inserita nel formato yyyy-mm-dd
	 * le date non sono inserite in ordine cronologico
	 * La seconda viene lanciata se: il file del tipo <cityname>.<dayI>(o <dayF>).txt non esiste
	 * @param cityname nome della città
	 * @param dayI giorno iniziale
	 * @param dayF giorno finale
	 * @throws DayErrorException eccezione riguardante le date
	 * @throws FileNotFoundException eccezione riguardante i file
	 */
	public void ErrorDays(String cityname, String dayI, String dayF) throws DayErrorException, FileNotFoundException
		{
			
				String path=System.getProperty("user.dir")+"\\" + cityname+"."+dayI+".txt";
				
				String nFileI=cityname+"."+dayI+".txt";
				String nFileF=cityname+"."+dayF+".txt";
				File file_out=new File(nFileI);
				if(dayI.length()!=10 ) throw new DayErrorException("inserire la data dayI come in questo esempio: yyyy-mm-dd");
				if(!file_out.exists()) throw new DayErrorException("inserire una data vecchia non più di 7 giorni e non dopo oggi ");
				
				
				
				
				if(!(dayF==null)) {
					file_out=new File(nFileF);
					if(dayF.length()!=10 ) throw new DayErrorException("inserire la data dayF come in questo esempio: yyyy-mm-dd");
					if(!file_out.exists()) throw new DayErrorException("inserire una data vecchia non più di 7 giorni e non dopo oggi");
					
				if(Integer.valueOf(dayI.substring(0, 4))>Integer.valueOf(dayF.substring(0, 4))) throw new DayErrorException("inserire le date in ordine cronologico ");
				if(Integer.valueOf(dayI.substring(5,7))>Integer.valueOf(dayF.substring(5,7))) throw new DayErrorException("inserire le date in ordine cronologico ");
				if(Integer.valueOf(dayI.substring(8))>Integer.valueOf(dayF.substring(8))) throw new DayErrorException("inserire le date in ordine cronologico ");
					
				}
				
				
			
					
			
		}
	/**
	 * Metodo che serve a lanciare l'eccezione HourErrorException
	 * Viene lanciata se: l'orario non è nel formato hh-hh, l'orario non è compreso tra 00 e 24, l'ora iniziale e l'ora finale
	 * non sono in ordine cronologico	
	 * @param period fascia oraria
	 * @throws HourErrorException eccezione riguardante l'ora
	 */
	public void ErrorHour(String period) throws HourErrorException 
		{
			if(period.length()!=5) throw new HourErrorException("orario deve essere scritto come questo esempio: 01-03 ");
			
			double hourI=Double.valueOf(period.substring(0, 2));
			Double hourF=Double.valueOf(period.substring(3));
			if(!(hourI<25&&hourI>-1)||(!(hourF<25&&hourF>-1)))  throw new HourErrorException("orario deve essere compreso tra 00 e 24 ");
			if(hourI >=hourF)	throw new HourErrorException("prima inserire l'ora iniziale, poi quella finale");
			
		}	
		}

