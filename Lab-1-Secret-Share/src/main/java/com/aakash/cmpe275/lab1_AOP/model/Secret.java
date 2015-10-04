package com.aakash.cmpe275.lab1_AOP.model;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class Secret {
	
	private UUID id;
	private String message;
	
	/**
	 * Default Constructor
	 */
	public Secret(){
		this.message="Default Secret";
	}
	
	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public Secret(String message){
		this.message = message;
	}
	
	/**
	 * method to generate unique UUID
	 */
	public void generateId(){
		this.id = UUID.randomUUID();
	}
	
	/**
	 * @return the UUID
	 */
	public UUID getId() {
		return id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}