package com.example.demo.model.dto;

/**
 * The Class ProjectResponseDto.
 */
public class ProjectResponseDto {
	
	/** The project id. */
	private Long projectId;

	/** The title. */
	private String title;

	/** The description. */
	private String description;

	/** The owner user id. */
	private Long owner_user_id;

	/** The size. */
	private Long size;

	/** The visibility. */
	private Boolean visibility;

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

	/**
	 * Gets the owner user id.
	 *
	 * @return the owner user id
	 */
	public Long getOwner_user_id() {
		return owner_user_id;
	}

	/**
	 * Sets the owner user id.
	 *
	 * @param owner_user_id the new owner user id
	 */
	public void setOwner_user_id(Long owner_user_id) {
		this.owner_user_id = owner_user_id;
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
