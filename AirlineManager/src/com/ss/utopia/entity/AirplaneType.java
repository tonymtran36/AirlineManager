package com.ss.utopia.entity;

import java.io.Serializable;

public class AirplaneType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer airplaneType;
	private Integer maxCapacity;
	
	public Integer getAirplaneType() {
		return airplaneType;
	}
	public void setAirplaneType(Integer airplaneType) {
		this.airplaneType = airplaneType;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	
}
