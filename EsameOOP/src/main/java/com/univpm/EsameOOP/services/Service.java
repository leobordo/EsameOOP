package com.univpm.EsameOOP.services;

import java.io.IOException;

import org.json.simple.JSONObject;
import com.univpm.EsameOOP.exception.*;
import com.univpm.EsameOOP.model.City;

/**
 * Interfaccia implementata da ServiceImpl
 * Contiene alcuni dei metodi richiamati dal controller
 * @author Leonardo Bordoni
 *@author Samuele Di Summa
 */
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
	public abstract String deleteFile(String path);
}
