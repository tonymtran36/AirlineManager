package com.ss.utopia.entity;

public class FlightBookings {

	private Flight flights = new Flight();
	//private Integer flights;
	private Booking bookingId = new Booking();
	
	public Flight getFlights() {
		return flights;
	}
	public void setFlights(Flight flights) {
		this.flights = flights;
	}
	public Booking getBookingId() {
		return bookingId;
	}
	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
}
