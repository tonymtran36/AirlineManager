package com.ss.utopia.entity;

import java.io.Serializable;

public class Airplane implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
