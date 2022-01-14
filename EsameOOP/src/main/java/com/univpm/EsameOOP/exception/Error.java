package com.univpm.EsameOOP.exception;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe che raggruppa insieme tutte i metodi che controllano gli errori di formato dei paramentri in ingresso
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class Error {

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
	 * La seconda viene lanciata se: il file del tipo cityname.dayI(o dayF).txt non esiste
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
