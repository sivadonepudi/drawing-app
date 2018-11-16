package com.sample.application.drawing.exception;

/**
 * Application specific exception
 * 
 * @author sidonepudi
 *
 */
public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2887569921577744328L;

	public InvalidInputException(String message) {
		super(message);
	}
}