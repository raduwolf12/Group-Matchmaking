package com.example.demo.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Class GroupPreference.
 */
@Entity
@Table(name = "pair_preferences")
public class PairPreference {

	/** The group preference id. */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long groupPreferenceId;

	/** The users. */
	@ManyToMany()
	@JoinTable(name = "pair_preferences_users", joinColumns = @JoinColumn(name = "pair_preferences_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	/** The group creator. */
	@OneToOne
	@JoinColumn(name = "group_creator_id")
	private User groupCreator;

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
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Gets the group creator.
	 *
	 * @return the group creator
	 */
	public User getGroupCreator() {
		return groupCreator;
	}

	/**
	 * Sets the group creator.
	 *
	 * @param groupCreator the new group creator
	 */
	public void setGroupCreator(User groupCreator) {
		this.groupCreator = groupCreator;
	}

}
