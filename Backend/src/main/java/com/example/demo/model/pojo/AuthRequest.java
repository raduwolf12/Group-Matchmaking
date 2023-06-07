package com.example.demo.model.pojo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * The Class AuthRequest.
 */
public class AuthRequest {

	/** The email. */
	@NotNull
	@Email
	@Length(min = 5)
	private String email;

	/** The password. */
	@NotNull
	@Length(min = 5)
	private String password;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
