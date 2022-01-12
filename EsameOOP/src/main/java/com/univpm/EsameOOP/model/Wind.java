package com.univpm.EsameOOP.model;

/**
 * Classe che descrive le informazioni riguardo il vento
 * @author Leonardo Bordoni
 *@author Samuele di Summa
 */
public class Wind {

	/**
	 * Attributi della classe
	 */
	private float degrees;
	private float gust;
	private float speed;
	/**
	 * Costruttore della classe
	 * @param degrees direzione del vento
	 * @param gust velocità delle raffiche di vento
	 * @param speed velocità del vent
	 */
	public Wind(float degrees, float gust, float speed) {
		
		this.degrees = degrees;
		this.gust = gust;
		this.speed = speed;
	}
	/**
	 * Metodo che ritorna la direzione del vento
	 * @return
	 */
	public float getDegrees() {
		return degrees;
	}
	/**
	 * Metodo che setta la direzione del vento
	 * @param degrees
	 */
	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}
	/**
	 * Metodo che ritorna la velocità delle raffiche
	 * @return
	 */
	public float getGust() {
		return gust;
	}
	/**
	 * Metodo che setta la velocità delle raffiche
	 * @param gust
	 */
	public void setGust(float gust) {
		this.gust = gust;
	}
	/**
	 * Metodo che ritorna la velocità del vento
	 * @return
	 */
	public float getSpeed() {
		return speed;
	}
	/**
	 * Metodo che setta la velocità del vento
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/**
	 * Metodo toString sovrascritto per Wind
	 * @return una stringa contenente le informazioni sul vento
	 */
	@Override
	public String toString() {
		return "Wind: degrees=" + degrees + ", gust=" + gust + ", speed=" + speed;
	}
}
