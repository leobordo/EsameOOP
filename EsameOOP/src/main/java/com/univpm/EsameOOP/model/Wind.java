package com.univpm.EsameOOP.model;

public class Wind {

	private float degrees;
	private float gust;
	private float speed;
	public Wind(float degrees, float gust, float speed) {
		
		this.degrees = degrees;
		this.gust = gust;
		this.speed = speed;
	}
	public float getDegrees() {
		return degrees;
	}
	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}
	public float getGust() {
		return gust;
	}
	public void setGust(float gust) {
		this.gust = gust;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	@Override
	public String toString() {
		return "Wind: degrees=" + degrees + ", gust=" + gust + ", speed=" + speed;
	}
}
