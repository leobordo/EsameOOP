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
	 
	

	@GetMapping(value="/General")
    public ResponseEntity<Object> getGeneralWeather(@RequestParam String cityname) throws IOException  {
		
		return new ResponseEntity<> (si.getGeneralWeather(cityname), HttpStatus.OK);
	}

	@GetMapping(value="/GetWind")
	public ResponseEntity<Object> getWind(@RequestParam String cityname) throws IOException  {
	
		return new ResponseEntity<> (si.getWind(cityname), HttpStatus.OK);
	}

	@GetMapping(value="/GetVisibility")
	public ResponseEntity<Object> getVisibility(@RequestParam String cityname) throws IOException  {
	
		return new ResponseEntity<> (si.getVisibility(cityname), HttpStatus.OK);
	}
	
	@GetMapping(value="/SaveHour")
	public ResponseEntity<Object> savehour(@RequestParam String cityname) throws IOException  {
	
		return new ResponseEntity<> (si.savehour(cityname), HttpStatus.OK);
	}
	
	@GetMapping(value="/Filters")
	public ResponseEntity<Object> choice(@RequestParam String cityname, String dayI, String dayF, String period) throws IOException  {
	
		return new ResponseEntity<> (f.choice(cityname, dayI, dayF, period), HttpStatus.OK);
	}
}
