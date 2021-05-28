package com.ss.utopia.entity;

import java.io.Serializable;

public class Airport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String airportCode;
	private String city;
	
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", city=" + city + "]";
	}
	
	
}
