package com.example.demo.model.entity;


import java.util.Set;

import com.example.demo.model.entity.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The Class User.
 * 
 * @author Radu
 */
@Entity
@Table(name = "users")
public class User {

	/** The user id. */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	Long userId;

	/** The name. */
	@Column(name = "name")
	String name;

	/** The canvas user id. */
	@Column(name = "canvas_user_id")
	Long canvasUserId;

	/** The group id. */
	@Column(name = "group_id")
	Long groupId;

	
	@OneToMany(mappedBy="user")
	private Set<ProjectPreference> projectPreferences;

	/** The role. */
	@Enumerated(EnumType.STRING)
	Role role;
	
	@OneToMany(mappedBy = "owner")
	private Set<Project> proposedProjects;
	

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the canvas user id.
	 *
	 * @return the canvas user id
	 */
	public Long getCanvasUserId() {
		return canvasUserId;
	}

	/**
	 * Sets the canvas user id.
	 *
	 * @param canvasUserId the new canvas user id
	 */
	public void setCanvasUserId(Long canvasUserId) {
		this.canvasUserId = canvasUserId;
	}

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
}
