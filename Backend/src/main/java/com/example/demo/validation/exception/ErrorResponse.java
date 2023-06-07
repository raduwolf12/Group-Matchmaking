package com.example.demo.validation.exception;

import java.time.LocalDateTime;

/**
 * The Class ErrorResponse.
 */
public class ErrorResponse {

	/** The message. */
	private String message;

	/** The statuscode. */
	private int statuscode;

	/** The date time. */
	private LocalDateTime dateTime;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the statuscode.
	 *
	 * @return the statuscode
	 */
	public int getStatuscode() {
		return statuscode;
	}

	/**
	 * Sets the statuscode.
	 *
	 * @param statuscode the new statuscode
	 */
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	/**
	 * Gets the date timel.
	 *
	 * @return the date timel
	 */
	public LocalDateTime getDateTimel() {
		return dateTime;
	}

	/**
	 * Sets the date timel.
	 *
	 * @param dateTime the new date timel
	 */
	public void setDateTimel(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}