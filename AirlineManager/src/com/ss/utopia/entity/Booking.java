package com.ss.utopia.entity;

import java.io.Serializable;

public class Booking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer booking;
	private boolean active; //0 or 1
	private transient String confirmCode;
	
	public Integer getBooking() {
		return booking;
	}
	public void setBooking(Integer booking) {
		this.booking = booking;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	
	
}
