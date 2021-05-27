package com.ss.utopia.entity;

public class BookingAgent {
	private Booking id = new Booking();
	private Integer agentId;
	
	public Booking getId() {
		return id;
	}
	public void setId(Booking id) {
		this.id = id;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	
}
