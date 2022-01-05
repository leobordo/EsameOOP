package com.univpm.EsameOOP.services;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.univpm.EsameOOP.model.City;

public interface Service {

	public abstract JSONObject getGeneralWeather(String city);
	public abstract String UNIXConverter(int time);
	public abstract JSONObject getVisibility(String city);
	public abstract JSONObject getWind(String city);
	public abstract City getVisibilityAndWind(String city);
	public abstract String save(String city) throws IOException;
	public abstract String savehour(String city);
	public abstract JSONObject readData(String fileName, String day) throws IOException;
	public abstract City createCity(String city);
	
	//DESCRIZIONE ANDAMENTO PROGETTO: 
	//LE STATISTICHE SI FANNO SOLO SU UN GIORNO(NON FUTURO),SU UNA FASCIA ORARIA(DI UN GIORNO O DI G. PASSATI)
	//IL CONTROLLER QUINDI AVRà UNA ROTTA DIVERSA A SECONDA CHE LE STATISTICHE SIANO IN UNA FASCIA ORARIA
	//O NEL GIORNO STESSO, INOLTRE BISOGNERà VEDERE SE CI RIFERISCE AD UN GIORNO PASSATO,PIù GIORNI PASSATI,
	//O AL GIORNO STESSO.
}
