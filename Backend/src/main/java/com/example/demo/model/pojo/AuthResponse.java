package com.example.demo.model.pojo;

/**
 * The Class AuthResponse.
 */
public class AuthResponse {
	
	/** The email. */
	private String email;
	
	/** The id. */
	private Long id;
	
	/** The access token. */
	private String accessToken;

	/**
	 * Instantiates a new auth response.
	 */
	public AuthResponse() {
	}

	/**
	 * Instantiates a new auth response.
	 *
	 * @param email the email
	 * @param id the id
	 * @param accessToken the access token
	 */
	public AuthResponse(String email, Long id, String accessToken) {
		this.email = email;
		this.id = id;
		this.accessToken = accessToken;
	}

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
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token.
	 *
	 * @param accessToken the new access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
