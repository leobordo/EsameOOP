package com.univpm.EsameOOP.controller;
import com.univpm.EsameOOP.services.ServiceImplementation;
import com.univpm.EsameOOP.utils.*;
import org.springframework.stereotype.Service ;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class controller {
	
	
	@Autowired
	
	ServiceImplementation si;
	Statistics a=new Statistics();
	Filters f=new Filters();
	 
	

	@GetMapping(value="/saveEveryHour")//ciao
    public ResponseEntity<Object> saveHour(@RequestParam String param1) throws IOException  {
		
	
		
		
		return new ResponseEntity<> (si.savehour(param1), HttpStatus.OK);
	}

@GetMapping(value="/readdata")//ciao
public ResponseEntity<Object> readData(@RequestParam String param1,String param2) throws IOException  {
	

	
	
	return new ResponseEntity<> (si.readData(param1,param2), HttpStatus.OK);
}
@GetMapping(value="/leggi")//ciao
public ResponseEntity<Object> today(@RequestParam String param1,String param2,String param3, String param4) throws IOException  {
	

	
	
	return new ResponseEntity<> (f.choice(param1, param2, param3, param4), HttpStatus.OK);
}

}
