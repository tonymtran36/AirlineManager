package com.ss.utopia.entity;

public class UserRole {
	private Integer roleId; //1 2 or 3
	private String name;
	
	private static final Integer ROLE_MAX_LENGTH = 45;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		StringBuilder temp = new StringBuilder(name);
		temp.setLength(ROLE_MAX_LENGTH);
		this.name = temp.toString();
		//this.name = name;
	}
	
	
}
