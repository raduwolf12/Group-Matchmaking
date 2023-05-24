package com.example.demo.model.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * The Class GroupPreference.
 */
@Entity
public class GroupPreference {
	
	/** The group preference id. */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long groupPreferenceId;

	/** The canvas user id. */
	@JsonBackReference
	@OneToOne(mappedBy = "groupPreference")
	private User groupOwner;
	
	/** The mates. */
	@JsonBackReference
	@OneToMany(mappedBy = "preference")
	private Set<User> mates;

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
	 * Gets the mates.
	 *
	 * @return the mates
	 */
	public Set<User> getMates() {
		return mates;
	}

	/**
	 * Sets the mates.
	 *
	 * @param mates the new mates
	 */
	public void setMates(Set<User> mates) {
		this.mates = mates;
	}

	public User getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(User groupOwner) {
		this.groupOwner = groupOwner;
	}

}
