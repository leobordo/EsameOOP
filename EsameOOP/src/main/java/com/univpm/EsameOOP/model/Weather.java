package com.univpm.EsameOOP.model;

public class Weather extends Wind{

	private long visibilty;
	

	public Weather(float degrees, float gust, float speed,long visibilty) {
		super(degrees, gust, speed);
		// TODO Auto-generated constructor stub
		this.visibilty=visibilty;
	}

	public long getVisibilty() {
		return visibilty;
	}

	public void setVisibilty(long visibilty) {
		this.visibilty = visibilty;
	}



	@Override
	public String toString() {
		return "Weather: visibilty=" + visibilty + "degrees=" + getDegrees() + ", gust=" + getGust() + ", speed=" + getSpeed();
	}
	
	
}
