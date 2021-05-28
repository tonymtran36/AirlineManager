package com.ss.utopia.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Flight implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer flightId;
	private Route routeId = new Route();
	private Airplane airplaneId = new Airplane();
	private LocalDate departureTime;
	private Integer reservedSeats;
	private Float seatPrice;
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public Route getRouteId() {
		return routeId;
	}
	public void setRouteId(Route routeId) {
		this.routeId = routeId;
	}
	public Airplane getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(Airplane airplaneId) {
		this.airplaneId = airplaneId;
	}
	public LocalDate getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDate departureTime) {
		this.departureTime = departureTime;
	}
	public Integer getReservedSeats() {
		return reservedSeats;
	}
	public void setReservedSeats(Integer reservedSeats) {
		this.reservedSeats = reservedSeats;
	}
	public Float getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(Float seatPrice) {
		this.seatPrice = seatPrice;
	}
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", Origin Route=" + routeId.getOriginAirport().getAirportCode() + ", " 
				+ routeId.getOriginAirport().getCity() + " Destination Route=" + routeId.getDestAirport().getAirportCode() + ", " 
						+ routeId.getDestAirport().getCity() + ", airplaneType=" + airplaneId.getAirplaneType().getAirplaneType() + ", "
						+ airplaneId.getAirplaneType().getMaxCapacity() + ", departureTime=" + departureTime + ", reservedSeats=" 
						+ reservedSeats + ", seatPrice=" + seatPrice + "]";
	}
	
	
}
