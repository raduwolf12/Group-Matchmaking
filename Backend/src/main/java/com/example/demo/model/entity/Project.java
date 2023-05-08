package com.example.demo.model.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The Class Project.
 */
@Entity
@Table(name = "projects")
public class Project {

	/** The project id. */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long projectId;

	/** The name. */
	@Column(name = "title")
	private String title;

	/** The canvas user id. */
	@Column(name = "description")
	private String description;

	/** The owner. */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User owner;

	/** The size. */
	@Column(name = "size")
	private Long size;

	/** The preference id. */
	@Column(name = "visibility")
	private Boolean visibility;

	/** The project preferences. */
	@OneToMany(mappedBy = "project")
	private Set<ProjectPreference> projectPreferences;

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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<ProjectPreference> getProjectPreferences() {
		return projectPreferences;
	}

	public void setProjectPreferences(Set<ProjectPreference> projectPreferences) {
		this.projectPreferences = projectPreferences;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * Gets the visibility.
	 *
	 * @return the visibility
	 */
	public Boolean getVisibility() {
		return visibility;
	}

	/**
	 * Sets the visibility.
	 *
	 * @param visibility the new visibility
	 */
	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

}
