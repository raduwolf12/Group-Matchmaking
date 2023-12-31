package com.example.demo.model.dto;

import com.example.demo.model.entity.enums.Role;
import com.example.demo.validation.validators.ValueOfEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDto.
 * 
 * @author Radu
 */
public class UserRequestDto {

	/** The user id. */
	@NotNull
	@Positive
	Long userId;

	/** The name. */
	@NotNull
	String name;

	/** The canvas user id. */
	@NotNull
	@Positive
	Long canvasUserId;

	/** The group id. */
	Long groupId;

	/** The preference id. */
	Long preferenceId;

	/** The is user paired. */
	boolean isUserPaired;

	/** The role. */
	@NotNull
	@ValueOfEnum(anyOf = { Role.PROFESSOR, Role.STUDENT, Role.TEACHER })
	Role role;

	/** The email. */
	private String email;

	/**
	 * Gets the checks if is user paired.
	 *
	 * @return the checks if is user paired
	 */
	public boolean getIsUserPaired() {
		return isUserPaired;
	}

	/**
	 * Sets the checks if is user paired.
	 *
	 * @param isUserPaired the new checks if is user paired
	 */
	public void setIsUserPaired(boolean isUserPaired) {
		this.isUserPaired = isUserPaired;
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