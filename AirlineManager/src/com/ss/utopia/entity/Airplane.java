package com.ss.utopia.entity;

public class Airplane {
	private Integer airplaneId;
	private AirplaneType airplaneType = new AirplaneType();
	
	public Integer getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}
	public AirplaneType getAirplaneType() {
		return airplaneType;
	}
	public void setAirplaneType(AirplaneType airplaneType) {
		this.airplaneType = airplaneType;
	}
	
	
}
