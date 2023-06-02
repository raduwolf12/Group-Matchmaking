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

	@ManyToMany()
	@JoinTable(
            name = "pair_preferences_users",
            joinColumns = @JoinColumn(name = "pair_preferences_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> users;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getGroupCreator() {
		return groupCreator;
	}

	public void setGroupCreator(User groupCreator) {
		this.groupCreator = groupCreator;
	}


}
