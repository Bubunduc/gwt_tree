package com.example.tree_rumyancev.shared.dto;

import java.sql.Timestamp;

public class ServerStatusViewData {
	private Timestamp time;
	private Long sereverId;
	private String status;
	
	public ServerStatusViewData(Timestamp time, Long sereverId, String status) {
		super();
		this.time = time;
		this.sereverId = sereverId;
		this.status = status;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Long getSereverId() {
		return sereverId;
	}
	public void setSereverId(Long sereverId) {
		this.sereverId = sereverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
