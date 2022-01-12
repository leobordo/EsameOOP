package com.univpm.EsameOOP.controller;
import com.univpm.EsameOOP.exception.CityErrorException;
import com.univpm.EsameOOP.exception.CustomNumberErrorException;
import com.univpm.EsameOOP.exception.DayErrorException;
import com.univpm.EsameOOP.exception.HourErrorException;
import com.univpm.EsameOOP.services.ServiceImplementation;
import com.univpm.EsameOOP.utils.*;
import org.springframework.stereotype.Service ;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller che gestisce le chiamate
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
@RestController
public class controller {
	
	/**
	 * Dichiarazione e creazione degli oggetti necessari
	 */
	@Autowired
	
	ServiceImplementation si;
	Filters f=new Filters();
	 
	
	/**
	 * Rotta che permette di ottenere il meteo generale di una certa città
	 * @param cityname nome della città
	 * @return ritorna il meteo della città
	 * @throws IOException eccezione di Input/Output
	 */
	@GetMapping(value="/General")
    public ResponseEntity<Object> getGeneralWeather(@RequestParam String cityname) throws IOException  {
		
		return new ResponseEntity<> (si.getGeneralWeather(cityname), HttpStatus.OK);
	}

	/**
	 * Rotta puramente  illustrativa che permette di ottenere le info del vento di una certa città
	 * @param cityname nome della città
	 * @return ritorna le info sul vento
	 * @throws IOException eccezione di Input/Output
	 */
	@GetMapping(value="/GetWind")
	public ResponseEntity<Object> getWind(@RequestParam String cityname) throws IOException  {
	
		return new ResponseEntity<> (si.getWind(cityname), HttpStatus.OK);
	}

	/**
	 * Rotta puramente  illustrativa che permette di ottenere le info della visibilità di una certa città
	 * @param cityname nome della città
	 * @return ritorna le info sulla visibilità
	 * @throws IOException eccezione di Input/Output
	 */
	@GetMapping(value="/GetVisibility")
	public ResponseEntity<Object> getVisibility(@RequestParam String cityname) throws IOException  {
	
		return new ResponseEntity<> (si.getVisibility(cityname), HttpStatus.OK);
	}
	
	/**
	 * Rotta che permette di ottenere i salvataggi orari delle previsioni su vento e visibilità di una certa città
	 * @param cityname nome della città
	 * @return ritorna una stringa di conferma del salvataggio
	 * @throws IOException eccezione di Input/Output
	 */
	@GetMapping(value="/SaveHour")
	public ResponseEntity<Object> savehour(@RequestParam String cityname) throws IOException  {
	
		
		return new ResponseEntity<> (si.savehour(cityname), HttpStatus.OK);
	}
	
	
	/**
	 * Rotta che esegue le statistiche dopo avere eseguito i dovuti filtraggi in base alla periodicità.
	 * Si possono avere statistiche giornaliere, settimanali, in una certa fascia oraria di un giorno o di più giorni
	 * I giorni vanno inseriti con il format yyyy-mm-dd, la fascia oraria invece hh-hh.
	 * A fini didattici il programma esegue solo statistiche su Ancona e Roma, vanno sempre inserite con la lettera maiuscola
	 * @param cityname nome della città(sempre necessario)
	 * @param dayI primo giorno di cui si vogliono le statistiche(sempre necessario)
	 * @param dayF ultimo giorno di cui si vogliono le statistiche(necessario solo per fascia di giorni e fascia oraria in più giorni)
	 * @param period fascia oraria di cui si vogliono le statistiche(necessario per fascia oraria di uno o più giorni)
	 * @return ritorna un JSONObject contenente le statistiche filtrate
	 * @throws HourErrorException eccezione che viene lanciata se c'è un errore nell'inserimento del period
	 * @throws CityErrorException eccezione che viene lanciata se c'è un errore nell'inserimento del cityname
	 * @throws DayErrorException eccezione che viene lanciata se c'è un errore nell'inserimento del dayI o dayF
	 * @throws IOException eccezione di Input/Output
	 * @throws CustomNumberErrorException eccezione che viene lanciata se vengono usate lettere anzichè numeri, eredita da NumberFormatException
	 * @throws NumberFormatException eccezione che viene gestita con l'eccezione personalizzata CustomNumberErrorException
	 */
	@GetMapping(value="/Filters")
	public ResponseEntity<Object> choice(@RequestParam String cityname, String dayI, String dayF, String period) 
	
	throws HourErrorException,CityErrorException,DayErrorException, IOException, CustomNumberErrorException, NumberFormatException
	{
		
	try {
		
		return new ResponseEntity<> (f.choice(cityname, dayI, dayF, period), HttpStatus.OK);
	}catch(NumberFormatException e)
	{
		
		e= new CustomNumberErrorException("usa solo numeri");
		
		return new ResponseEntity<>(((CustomNumberErrorException) e).getText(),HttpStatus.BAD_REQUEST);
	}
	catch(HourErrorException e)
	{
		return new ResponseEntity<>(e.getText(),HttpStatus.BAD_REQUEST);
	}
	
	catch(CityErrorException e)
	{
		return new ResponseEntity<>(e.getText(),HttpStatus.BAD_REQUEST);
	}
	catch(DayErrorException e)
	{
		return new ResponseEntity<>(e.getText(),HttpStatus.BAD_REQUEST);
	}
		
	}
}
