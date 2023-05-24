package com.example.demo.model.dto;

import java.util.Set;

/**
 * The Class GroupPreferenceRequestDto.
 */
public class GroupPreferenceRequestDto {
	
	/** The group preference id. */
	Long groupPreferenceId;

	/** The group owner. */
	private Long groupOwner;

	/** The mates. */
	private Set<Long> mates;

	/**
	 * Gets the group preference id.
	 *
	 * @return the group preference id
	 */
	public Long getGroupPreferenceId() {
		return groupPreferenceId;
	}

	/**
	 * Sets the group preference id.
	 *
	 * @param groupPreferenceId the new group preference id
	 */
	public void setGroupPreferenceId(Long groupPreferenceId) {
		this.groupPreferenceId = groupPreferenceId;
	}

	/**
	 * Gets the group owner.
	 *
	 * @return the group owner
	 */
	public Long getGroupOwner() {
		return groupOwner;
	}

	/**
	 * Sets the group owner.
	 *
	 * @param groupOwner the new group owner
	 */
	public void setGroupOwner(Long groupOwner) {
		this.groupOwner = groupOwner;
	}

	/**
	 * Gets the mates.
	 *
	 * @return the mates
	 */
	public Set<Long> getMates() {
		return mates;
	}

	/**
	 * Sets the mates.
	 *
	 * @param mates the new mates
	 */
	public void setMates(Set<Long> mates) {
		this.mates = mates;
	}

}
