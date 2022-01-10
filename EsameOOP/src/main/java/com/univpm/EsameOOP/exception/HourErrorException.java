package com.univpm.EsameOOP.exception;

import java.util.Arrays;

public class HourErrorException extends Exception {
	String text;

	public HourErrorException(String text) {
		super();
		this.text = text;
	}



	public String getText() {
		return text;
	}
}
