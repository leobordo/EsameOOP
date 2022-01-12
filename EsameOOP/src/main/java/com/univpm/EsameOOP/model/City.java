package com.univpm.EsameOOP.model;
import java.util.*;

/**
 * Questa classe modella l'oggetto Città 
 * Eredita da GeoPosition
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class City extends GeoPosition{
	/**
	 * serie di attributi private che modellano la classe city
	 */
	private String name;
	private String country;
	private ArrayList<Weather> predictions = new ArrayList<>();
	/**
	 * costruttore della clase
	 * @param coordinates sono le coordinate della città
	 * @param id il codice numerico che openweather assegna alla città
	 * @param name il nome della città
	 * @param country la nazione della città
	 */
	public City(Coordinates coordinates, int id, String name, String country)
	{
		super(coordinates, id);
		this.country=country;
		this.name=name;
		
	}
	
	/**
	 * Metodo che ritorna il nome della città
	 * @return il nome
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metodo che setta il nome della città
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Metodo che ritorna la nazione della città
	 * @return
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Metodo che setta la nazione della città
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Metodo che ritorna l'ArrayList di previsioni per la città
	 * @return
	 */
	public ArrayList<Weather> getPredictions() {
		return predictions;
	}
	/**
	 * Metodo che setta l'ArrayList di previsoni per la città
	 * @param predictions
	 */
	public void setPredictions(ArrayList<Weather> predictions) {
		this.predictions = predictions;
	}
	/**
	 * toString sovrascritto per City
	 * @return ritorna una stringa con le info della città
	 */
	@Override
	public String toString() {
		return "City: name=" + name + ", country=" + country +"id=" + getId() +"latitude=" + getLatitude() + ", longitude=" + getLongitude()+ ", predictions=" + predictions;
	}
	
}
