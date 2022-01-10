package com.univpm.EsameOOP.exception;


public class CustomNumberErrorException extends NumberFormatException {
		String text;

		public CustomNumberErrorException(String text) {
			super();
			this.text = text;
		}

		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text=text;
		}
	}


