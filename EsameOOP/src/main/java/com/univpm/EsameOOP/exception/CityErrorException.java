package com.univpm.EsameOOP.exception;



public class CityErrorException extends Exception {
	String text;

	public CityErrorException(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
