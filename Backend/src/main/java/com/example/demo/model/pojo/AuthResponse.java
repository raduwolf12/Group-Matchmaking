package com.example.demo.model.pojo;

public class AuthResponse {
	private String email;
	private Long id;
	private String accessToken;

	public AuthResponse() {
	}

	public AuthResponse(String email, Long id, String accessToken) {
		this.email = email;
		this.id = id;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
