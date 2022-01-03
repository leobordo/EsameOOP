package com.univpm.EsameOOP.model;

public class Weather extends Wind{

	private int visibilty;
	private String date;
	

	public Weather(float degrees, float gust, float speed,int visibilty,String date) {
		super(degrees, gust, speed);
		// TODO Auto-generated constructor stub
		this.visibilty=visibilty;
		this.date=date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getVisibilty() {
		return visibilty;
	}

	public void setVisibilty(int visibilty) {
		this.visibilty = visibilty;
	}



	@Override
	public String toString() {
		return "Weather: visibilty=" + visibilty + "degrees=" + getDegrees() + ", gust=" + getGust() + ", speed=" + getSpeed();
	}
	
	
}
