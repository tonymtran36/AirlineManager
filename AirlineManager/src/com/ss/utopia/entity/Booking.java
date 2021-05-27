package com.ss.utopia.entity;

import java.io.Serializable;

public class Booking implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -7621301927254900943L;
	private Integer booking;
	private boolean active; //0 or 1
	private String confirmCode;
	
	private static final Integer CODE_MAX_LENGTH = 255;
	
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
		StringBuilder temp = new StringBuilder(confirmCode);
		temp.setLength(CODE_MAX_LENGTH);
		this.confirmCode = temp.toString();
		//this.confirmCode = confirmCode;
	}
	
	
}
