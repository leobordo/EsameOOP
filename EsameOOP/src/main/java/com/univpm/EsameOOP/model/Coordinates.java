package com.univpm.EsameOOP.model;

/**
 * Classe che descrive le coordinate di un punto nello spazio
 * @author Bordoni Leonardo
 * @author Samuele Di Summa
 *
 */
public class Coordinates {
	/**
	 * Attributi della classe
	 */
	private double latitude;
	private double longitude;
	
	/**
	 * Costruttore della classe
	 * @param latitude latitudine del punto
	 * @param longitude longitudine del punto
	 */
	public Coordinates(double latitude, double longitude)
	{
		this.latitude=latitude;
		this.longitude=longitude;
	}

	/**
	 * Metodo che ritorna la latitudine
	 * @return
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Metodo che setta la latitudine
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Metodo che ritorna la longitudine
	 * @return
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Metodo che setta la longitudine
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Metodo toString sovrascritto per Coordinates
	 * @return ritorna una stringa contenente le coordinate di un punto nello spazio
	 */
	@Override
	public String toString() {
		return "Coordinates: latitude=" + latitude + ", longitude=" + longitude;
	}
	
	
}
