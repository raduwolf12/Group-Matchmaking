package com.example.demo.model.pojo;

/**
 * The Class ResponseMessage.
 */
public class ResponseMessage {

	/** The message. */
	private String message;

	/**
	 * Instantiates a new response message.
	 *
	 * @param message the message
	 */
	public ResponseMessage(String message) {
		this.message = message;
	}

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
}
