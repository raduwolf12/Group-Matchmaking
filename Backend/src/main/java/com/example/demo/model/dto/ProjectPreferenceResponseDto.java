package com.example.demo.model.dto;

public class ProjectPreferenceResponseDto {

	Long projectPreferenceId;

	Long userId;

	Integer rank;

	Long projectId;

	public Long getProjectPreferenceId() {
		return projectPreferenceId;
	}

	public void setProjectPreferenceId(Long projectPreferenceId) {
		this.projectPreferenceId = projectPreferenceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
