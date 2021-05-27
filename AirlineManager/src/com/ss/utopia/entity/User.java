package com.ss.utopia.entity;

public class User {
	private Integer userId;
	private UserRole roleId = new UserRole();
	private String name;
	private String familyName;
	private String username;
	private String email;
	private String password;
	private String phoneNum;

	private static final Integer USER_PHONE_MAX_LENGTH = 45;
	private static final Integer MAX_LENGTH = 255;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public UserRole getRoleId() {
		return roleId;
	}
	public void setRoleId(UserRole roleId) {
		this.roleId = roleId;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		StringBuilder temp = new StringBuilder(username);
		temp.setLength(USER_PHONE_MAX_LENGTH);
		this.username = temp.toString();
		//this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		StringBuilder temp = new StringBuilder(email);
		temp.setLength(MAX_LENGTH);
		this.email = temp.toString();
		//this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		StringBuilder temp = new StringBuilder(password);
		temp.setLength(MAX_LENGTH);
		this.password = temp.toString();		
		//this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		StringBuilder temp = new StringBuilder(phoneNum);
		temp.setLength(USER_PHONE_MAX_LENGTH);
		this.phoneNum = temp.toString();		
		//this.phoneNum = phoneNum;
	}
	
	
}
