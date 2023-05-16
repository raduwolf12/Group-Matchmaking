package com.example.demo.model.dto;

public class ProjectResponseDto {
	private Long projectId;

	private String title;

	private String description;

	private Long owner_user_id;

	private Long size;

	private Boolean visibility;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOwner_user_id() {
		return owner_user_id;
	}

	public void setOwner_user_id(Long owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}
}
