package com.example.demo.validation.exception;

/**
 * The Class ProjectNotFoundException.
 */
public class ProjectNotFoundException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new customer not found exception.
	 *
	 * @param message the message
	 */
	public ProjectNotFoundException(String message) {
		super(message);
	}
}