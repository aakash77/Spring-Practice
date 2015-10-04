package com.aakash.cmpe275.lab1_AOP.exceptions;

/**
 * This is Custom Runtime Exception
 * @author Aakash Mangal
 *
 */
public class UnauthorizedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message = null;
	
    public UnauthorizedException() {
        super();
    }
    
    /**
     * @param message
     */
    public UnauthorizedException(String message) {
        super(message);
        this.message = message;
    }
    
    /**
     * @param cause
     */
    public UnauthorizedException(Throwable cause) {
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