package com.example.demo.model.dto;

import java.util.Set;

/**
 * The Class FinalGroupResponseDto.
 */
public class FinalGroupResponseDto {
	
	/** The id. */
	Long id;

	/** The members id. */
	Set<Long> membersId;

	/** The project id. */
	Long projectId;

	/** The solo slots. */
	int soloSlots;

	/** The pair slots. */
	int pairSlots;

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

	/**
	 * Gets the members id.
	 *
	 * @return the members id
	 */
	public Set<Long> getMembersId() {
		return membersId;
	}

	/**
	 * Sets the members id.
	 *
	 * @param membersId the new members id
	 */
	public void setMembersId(Set<Long> membersId) {
		this.membersId = membersId;
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

	/**
	 * Gets the solo slots.
	 *
	 * @return the solo slots
	 */
	public int getSoloSlots() {
		return soloSlots;
	}

	/**
	 * Sets the solo slots.
	 *
	 * @param soloSlots the new solo slots
	 */
	public void setSoloSlots(int soloSlots) {
		this.soloSlots = soloSlots;
	}

	/**
	 * Gets the pair slots.
	 *
	 * @return the pair slots
	 */
	public int getPairSlots() {
		return pairSlots;
	}

	/**
	 * Sets the pair slots.
	 *
	 * @param pairSlots the new pair slots
	 */
	public void setPairSlots(int pairSlots) {
		this.pairSlots = pairSlots;
	}

}
