package com.ss.utopia.entity;

import java.sql.Date;

public class Passenger {

	private Integer passengerId;
	private Booking bookingId = new Booking();
	private String name;
	private String familyName;
	private Date birthdate;
	private String gender;
	private String address;
	
	private static final Integer GENDER_ADDRESS_LENGTH = 45;
	private static final Integer MAX_LENGTH = 255;
	
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public Booking getBookingId() {
		return bookingId;
	}
	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		StringBuilder temp = new StringBuilder(name);
		temp.setLength(MAX_LENGTH);
		this.name = temp.toString();
		//this.name = name;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		StringBuilder temp = new StringBuilder(familyName);
		temp.setLength(MAX_LENGTH);
		this.familyName = temp.toString();
		//this.familyName = familyName;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		StringBuilder temp = new StringBuilder(gender);
		temp.setLength(GENDER_ADDRESS_LENGTH);
		this.gender = temp.toString();
		//this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		StringBuilder temp = new StringBuilder(address);
		temp.setLength(GENDER_ADDRESS_LENGTH);
		this.address = temp.toString();
		//this.address = address;
	}
	
	
	
}
