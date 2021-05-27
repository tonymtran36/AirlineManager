package com.ss.utopia.entity;

public class Route {

	private Integer id;
	private Airport originAirport = new Airport();
	private Airport destAirport = new Airport();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Airport getOriginAirport() {
		return originAirport;
	}
	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}
	public Airport getDestAirport() {
		return destAirport;
	}
	public void setDestAirport(Airport destAirport) {
		this.destAirport = destAirport;
	}
	
	
}
