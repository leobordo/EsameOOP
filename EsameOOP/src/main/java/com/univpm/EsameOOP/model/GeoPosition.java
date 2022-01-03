package com.univpm.EsameOOP.model;

public class GeoPosition extends Coordinates{
	private int id;
	
	public GeoPosition(Coordinates coordinates, int id)
	{
		super(coordinates.getLatitude(),coordinates.getLongitude());
		this.id=id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GeoPosition: id=" + id +"latitude=" + getLatitude() + ", longitude=" + getLongitude();
	}
	
}
