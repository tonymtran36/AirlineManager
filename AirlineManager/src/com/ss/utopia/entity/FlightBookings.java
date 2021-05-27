package com.ss.utopia.entity;

import java.io.Serializable;

public class FlightBookings implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5584707780534654998L;
	//private List<Flight> flights;
	//private Flight flights = new Flight();
	private Integer flights;
	private Booking bookingId = new Booking();
	
	public Integer getFlights() {
		return flights;
	}
	public void setFlights(Integer flights) {
		this.flights = flights;
	}
	public Booking getBookingId() {
		return bookingId;
	}
	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
}
