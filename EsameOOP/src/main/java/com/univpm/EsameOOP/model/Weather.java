package com.univpm.EsameOOP.model;

/**
 * Classe che modella una previsione meteo
 * Eredita dalla classe Wind
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 */
public class Weather extends Wind{

	/**
	 * Atrributi della classe
	 */
	private int visibilty;
	private String date;
	

	/**
	 * Costruttore della classe
	 * @param degrees direzione del vento
	 * @param gust velocità delle raffiche di vento
	 * @param speed velocità del vento
	 * @param visibilty visibilità
	 * @param date data in cui viene fatta la previsione
	 */
	public Weather(float degrees, float gust, float speed,int visibilty,String date) {
		super(degrees, gust, speed);
		// TODO Auto-generated constructor stub
		this.visibilty=visibilty;
		this.date=date;
	}

	/**
	 * Metodo che ritorna la data della previsione
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Metodo che setta la data della previsione
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Metodo che ritorna la visibilità
	 * @return
	 */
	public int getVisibilty() {
		return visibilty;
	}

	/**
	 * Metodo che setta la visibilità
	 * @param visibilty
	 */
	public void setVisibilty(int visibilty) {
		this.visibilty = visibilty;
	}


	/**
	 * Metodo tostring sovrascritto per Weather
	 * @return ritorna una stringa contenente le informazioni sulla previsone meteo
	 */
	@Override
	public String toString() {
		return "Weather: visibilty=" + visibilty + "degrees=" + getDegrees() + ", gust=" + getGust() + ", speed=" + getSpeed();
	}
	
	
}
