package com.consumer.notifications.consumer;

public class UserDetails {

	private String userId;
	private String remBalance;
	
	public UserDetails() {}
	
	public UserDetails(String userId, String remBalance) {
		super();
		this.userId = userId;
		this.remBalance = remBalance;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemBalance() {
		return remBalance;
	}
	public void setRemBalance(String remBalance) {
		this.remBalance = remBalance;
	}
	
	
}