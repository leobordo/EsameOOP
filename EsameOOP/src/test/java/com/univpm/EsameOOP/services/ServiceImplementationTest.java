package com.univpm.EsameOOP.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che implementa i test del ServiceImplementation
 * @author Leonardo Bordoni
 * @author Samuele Di Summa
 *
 */
public class ServiceImplementationTest {

	private ServiceImplementation s;
	
	/**
	 * Set up di tutte le variabili necessarie
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		s=new ServiceImplementation();
	}
	
	/**
	 * Elimina tutte le variabili usate non più necessarie
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test che controlla il corretto display del messaggio a seguito del metodo save
	 * @throws IOException
	 */
	@Test
	@DisplayName("Salvataggio corretto")
	void salva() throws IOException{
		String n="Ancona";
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String today = date.format(new Date());
		String nFile=n+"."+today+".txt";
		
		assertEquals(nFile,s.save(n));
	}
	
	/**
	 * Test che controlla il corretto display del messaggio a seguito del metodo deleteFile
	 * la directory in questo caso contiene meno di 7 file
	 */
	@Test
	@DisplayName("Eliminazione corretta")
	void elimina() {
		String path=System.getProperty("user.dir")+"\\Roma\\";
		assertEquals("nessun file eliminato",s.deleteFile(path));
	}
	
	
	}

