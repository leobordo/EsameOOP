package com.univpm.EsameOOP.model;
import java.util.*;

public class City extends GeoPosition{
	private String name;
	private String country;
	private ArrayList<Weather> predictions = new ArrayList<>();
	public City(Coordinates coordinates, long id, String name, String country)
	{
		super(coordinates, id);
		this.country=country;
		this.name=name;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ArrayList<Weather> getPredictions() {
		return predictions;
	}
	public void setPredictions(ArrayList<Weather> predictions) {
		this.predictions = predictions;
	}
	@Override
	public String toString() {
		return "City: name=" + name + ", country=" + country +"id=" + getId() +"latitude=" + getLatitude() + ", longitude=" + getLongitude()+ ", predictions=" + predictions;
	}
	
}
