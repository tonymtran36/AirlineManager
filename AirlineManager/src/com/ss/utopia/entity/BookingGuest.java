package com.ss.utopia.entity;

public class BookingGuest {
	private Booking id;
	private String email;
	private String phoneNum;
	
	private static final Integer EMAIL_MAX_LENGTH = 255;
	private static final Integer PHONE_MAX_LENGTH = 45;
	
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
		StringBuilder temp = new StringBuilder(email);
		temp.setLength(EMAIL_MAX_LENGTH);
		this.email = temp.toString();
		//this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		StringBuilder temp = new StringBuilder(phoneNum);
		temp.setLength(PHONE_MAX_LENGTH);
		this.phoneNum = temp.toString();
		//this.phoneNum = phoneNum;
	}

	
}
