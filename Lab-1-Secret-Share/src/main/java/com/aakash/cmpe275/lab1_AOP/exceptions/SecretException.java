package com.aakash.cmpe275.lab1_AOP.exceptions;

public class SecretException extends Exception {
	
	private String message = null;
	 
    public SecretException() {
        super();
    }
 
    public SecretException(String message) {
        super(message);
        this.message = message;
    }
 
    public SecretException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}