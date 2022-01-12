package com.univpm.EsameOOP.exception;


/**
 * Classe per deifinire l'eccezione CustomNumberErrorException, estende NumberFormatException
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class CustomNumberErrorException extends NumberFormatException {
	/**
	 * Stringa  che serve a settare il messaggio di errore
	 */	
	String text;

	/**
	 * Costruttore, setta il messaggio di errore
	 * @param text
	 */	
	public CustomNumberErrorException(String text) {
			super();
			this.text = text;
		}

	/**
	 * ritorna il messaggio di errore
	 * @return
	 */
		public String getText() {
			return text;
		}
		
	}


