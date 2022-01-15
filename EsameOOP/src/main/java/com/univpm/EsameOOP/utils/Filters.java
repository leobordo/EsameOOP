package com.univpm.EsameOOP.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import com.univpm.EsameOOP.exception.*;
import com.univpm.EsameOOP.exception.Error;
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
			Error e=new Error();
			e.ErrorCity(cityname);
			if(!(dayF==null)&&dayF.isEmpty()) dayF=null;

			if(!(period==null)&&period.isEmpty()) period=null;

			e.ErrorDays(cityname,dayI,dayF);
			if(!(period==null))e.ErrorHour(period);
			
			
			JSONObject toReturn=new JSONObject();
			Statistics s=new Statistics();
			//le righe sopra servono a risolvere errori su postman, da qui in poi si fa
			//la selezione di quali filtri usare e come usarli
			if(dayF==null && period==null) {
				
				//si filtrano le statistiche in una giornata
				toReturn=s.dailyStats(cityname, dayI);
			}else
				{if(dayF==null)
				
						//si filtrano le statistiche in una fascia oraria
						toReturn=s.dailyStatsPlus(cityname, dayI, period);
						
					
				else 
				{
					// da qui in poi si eseguono i filtraggi delle statistiche sulle fasce di giorni
					// e sulle fasce orarie nelle fasce di giorni.
					// si utilizza spesso l'operatore condizionale ?...:... per vedere se la fascia oraria(period) è nulla.
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
						String Year=dayI.substring(0,5); //se i giorni non sono nello stesso mese si tiene solo l'anno
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
						//bisogna controllare di volta in volta in quale mese si sta operando per far progredire normalmente i giorni
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

		}

