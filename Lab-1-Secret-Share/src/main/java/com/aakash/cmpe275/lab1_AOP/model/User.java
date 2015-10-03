package com.aakash.cmpe275.lab1_AOP.model;

import org.springframework.stereotype.Repository;

@Repository
public class User {

	private String userId;
	
	public User(String userId){
		this.userId = userId;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}