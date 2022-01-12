package com.univpm.EsameOOP.model;

/**
 * Questa classe contiene attributi e metodi per descrivere la posizione di una città
 * Eredita da Coordinates
 * @author Leonardo Bordoni
 *@author Samuele Di Summa
 */
public class GeoPosition extends Coordinates{
	/**
	 * Attributi della classe
	 */
	private int id;
	
	/**
	 * Costruttore della classe
	 * @param coordinates coordinate della città
	 * @param id codice numerico che openweather assegna ad ogni città
	 */
	public GeoPosition(Coordinates coordinates, int id)
	{
		super(coordinates.getLatitude(),coordinates.getLongitude());
		this.id=id;
	}

	/**
	 * Metodo che ritrona l'id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * metodo che setta l'id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * toString sovrascritto per GeoPosition
	 * @return ritorna una stringa con le informazioni sulla posizione geografica della città
	 */
	@Override
	public String toString() {
		return "GeoPosition: id=" + id +"latitude=" + getLatitude() + ", longitude=" + getLongitude();
	}
	
}
