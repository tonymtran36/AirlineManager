package com.ss.utopia.entity;

public class Airport {

	private String airportCode;
	private String city;
	private static final int CITY_MAX_LENGTH = 45;
	private static final int APCODE_MAX_LENGTH = 3;
	
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		//this.airportCode = airportCode;
		StringBuilder temp = new StringBuilder(airportCode);
		temp.setLength(APCODE_MAX_LENGTH);
		this.airportCode = temp.toString();
		
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		StringBuilder temp = new StringBuilder(city);
		temp.setLength(CITY_MAX_LENGTH);
		this.city = temp.toString();
		//this.city = city;
	}
	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", city=" + city + "]";
	}
	
	
}
