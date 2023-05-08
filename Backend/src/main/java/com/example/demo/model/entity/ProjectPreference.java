package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectPreference.
 */
@Entity
@Table(name = "project_preferences")
public class ProjectPreference {
	
	/** The project preference id. */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long projectPreferenceId;

	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	User user;

	/** The name. */
	@Column(name = "rank")
	Integer rank;

	/** The preference id. */
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	Project project;

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
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

}
