package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;

/**
 * The Class ProjectPreferenceRequestDto.
 */
public class ProjectPreferenceRequestDto {

	/** The project preference id. */
	Long projectPreferenceId;

	/** The user id. */
	@NotNull
	Long userId;

	/** The rank. */
	@NotNull
	Integer rank;

	/** The project id. */
	@NotNull
	Long projectId;

	/**
	 * Gets the project preference id.
	 *
	 * @return the project preference id
	 */
	public Long getProjectPreferenceId() {
		return projectPreferenceId;
	}

	/**
	 * Sets the project preference id.
	 *
	 * @param projectPreferenceId the new project preference id
	 */
	public void setProjectPreferenceId(Long projectPreferenceId) {
		this.projectPreferenceId = projectPreferenceId;
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
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * Sets the rank.
	 *
	 * @param rank the new rank
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
