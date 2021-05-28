package com.ss.utopia.entity;

public class BookingGuest {
	private Booking id;
	private String email;
	private String phoneNum;
	
	public Booking getId() {
		return id;
	}
	public void setId(Booking id) {
		this.id = id;
	}	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	
}
