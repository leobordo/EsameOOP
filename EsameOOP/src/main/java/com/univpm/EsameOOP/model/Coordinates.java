package com.univpm.EsameOOP.model;

public class Coordinates {
	private long latitude;
	private long longitude;
	
	public Coordinates(long latitude, long longitude)
	{
		this.latitude=latitude;
		this.longitude=longitude;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Coordinates: latitude=" + latitude + ", longitude=" + longitude;
	}
	
	
}
