package com.example.demo.model.dto;

import com.example.demo.model.entity.enums.Role;

/**
 * The Class UserDto.
 * 
 * @author Radu
 */
public class UserDto {

	/** The user id. */
	Long userId;

	/** The name. */
	String name;

	/** The canvas user id. */
	Long canvasUserId;

	/** The group id. */
	Long groupId;

	/** The preference id. */
	Long preferenceId;

	/** The role. */
	Role role;

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