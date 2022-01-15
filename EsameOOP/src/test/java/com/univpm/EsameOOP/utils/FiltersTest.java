package com.univpm.EsameOOP.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.univpm.EsameOOP.exception.CityErrorException;
import com.univpm.EsameOOP.exception.DayErrorException;
import com.univpm.EsameOOP.exception.HourErrorException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


/**
 * Classe che controlla il funzionamento della classe Filters
 * @author Bordoni Leonardo
 * @author Samuele Di Summa
 *
 */
public class FiltersTest {

private Filters f;
	
/**
 * Set up delle variabili necessarie	
 * @throws Exception
 */
@BeforeEach
	void setUp() throws Exception {
		f=new Filters();
	}
	
/**
 * Distruzione delle variabili usate non più necessarie	
 * @throws Exception
 */
@AfterEach
	void tearDown() throws Exception {
	}
	
/**
 * Test che verifica il corretto utilizzo dell'eccezione CityErrorException
 */
	@Test
	@DisplayName("Eccezione CityErrorException,per nome sbagliato, generata correttamente")
	void test1() {
		
		CityErrorException e=assertThrows(CityErrorException.class,()->{f.choice("Rome", "2022-01-17", null, null);});
		
		assertEquals("il nome della città non è in elenco (usa Roma o Ancona)",e.getText());
	}
	
	/**
	 * Test che verifica il corretto utilizzo dell'eccezione DayErrorException
	 */
	@Test
	@DisplayName("Eccezione DayErrorException,per dayF non nel giusto formato, generata correttamente")
	void test2() {
		DayErrorException e=assertThrows(DayErrorException.class,()->{f.choice("Roma", "2022-01-17", "20-2201-abc", null);});
		assertEquals("inserire la data dayF come in questo esempio: yyyy-mm-dd",e.getText());
		
	}
	
	/**
	 * Test che verifica il corretto utilizzo dell'eccezione DayErrorException
	 */
	@Test
	@DisplayName("Eccezione DayErrorException,per dayF nel giusto formato ma con valori casuali, generata correttamente")
	void test3() {
		DayErrorException e=assertThrows(DayErrorException.class,()->{f.choice("Roma", "2022-01-17", "20-2201-ab", null);});
		assertEquals("inserire una data vecchia non più di 7 giorni e non dopo oggi",e.getText());
		
	}
	
	/**
	 * Test che verifica il corretto utilizzo dell'eccezione HourErrorException
	 */
	@Test
	@DisplayName("Eccezione HourErrorException,per period errato, generata correttamente")
	void test4() {
		HourErrorException e=assertThrows(HourErrorException.class,()->{f.choice("Roma", "2022-01-17", null, "03-34");});
		assertEquals("orario deve essere compreso tra 00 e 24 ",e.getText());
		
	}
	
	/**
	 * Test che verifica il corretto utilizzo dell'eccezione HourErrorException
	 */
	@Test
	@DisplayName("Eccezione HourErrorException,per period non cronologico, generata correttamente")
	void test5() {
		HourErrorException e=assertThrows(HourErrorException.class,()->{f.choice("Roma", "2022-01-17", null, "03-02");});
		assertEquals("prima inserire l'ora iniziale, poi quella finale",e.getText());
		
	}
}
