package com.ss.utopia.entity;

import java.time.LocalDate;

public class Flight {
	private Integer flightId;
	private Route routeId = new Route();
	private Airplane airplaneId = new Airplane();
//	private Date departureTime;
//	private LocalDateTime departureTime;
	private LocalDate departureTime;
	private Integer reservedSeats;
	private Integer seatPrice;
	
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
	public Integer getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(Integer seatPrice) {
		this.seatPrice = seatPrice;
	}
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", routeId=" + routeId + ", airplaneId=" + airplaneId
				+ ", departureTime=" + departureTime + ", reservedSeats=" + reservedSeats + ", seatPrice=" + seatPrice
				+ "]";
	}
	
	
}
