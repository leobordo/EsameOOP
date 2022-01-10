package com.univpm.EsameOOP.exception;

import java.util.Arrays;

public class DayErrorException extends Exception {
	String text;

	public DayErrorException(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
