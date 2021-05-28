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
		this.name = name;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {		
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {	
		this.phoneNum = phoneNum;
	}
	
	
}
