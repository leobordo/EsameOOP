package com.univpm.EsameOOP.model;

public class GeoPosition extends Coordinates{
	private long id;
	
	public GeoPosition(Coordinates coordinates, long id)
	{
		super(coordinates.getLatitude(),coordinates.getLongitude());
		this.id=id;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GeoPosition: id=" + id +"latitude=" + getLatitude() + ", longitude=" + getLongitude();
	}
	
}
