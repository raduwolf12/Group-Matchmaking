package com.example.demo.model.dto;

import com.example.demo.model.entity.enums.Role;

// TODO: Auto-generated Javadoc
/**
 * The Class UserResponseDto.
 */
public class UserResponseDto {
	/** The user id. */
	private Long userId;

	/** The name. */
	private String name;

	/** The canvas user id. */
	private Long canvasUserId;

	/** The group id. */
	private Long groupId;

	/** The preference id. */
	private Long preferenceId;

	/** The role. */
	private Role role;

	/** The password. */
	private String password;

	/** The email. */
	private String email;

	/** The is user paired. */
	private Boolean isUserPaired;

	/**
	 * Gets the checks if is user paired.
	 *
	 * @return the checks if is user paired
	 */
	public Boolean getIsUserPaired() {
		return isUserPaired;
	}

	/**
	 * Sets the checks if is user paired.
	 *
	 * @param isUserPaired the new checks if is user paired
	 */
	public void setIsUserPaired(Boolean isUserPaired) {
		this.isUserPaired = isUserPaired;
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
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the canvas user id.
	 *
	 * @return the canvas user id
	 */
	public Long getCanvasUserId() {
		return canvasUserId;
	}

	/**
	 * Sets the canvas user id.
	 *
	 * @param canvasUserId the new canvas user id
	 */
	public void setCanvasUserId(Long canvasUserId) {
		this.canvasUserId = canvasUserId;
	}

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * Gets the preference id.
	 *
	 * @return the preference id
	 */
	public Long getPreferenceId() {
		return preferenceId;
	}

	/**
	 * Sets the preference id.
	 *
	 * @param preferenceId the new preference id
	 */
	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
