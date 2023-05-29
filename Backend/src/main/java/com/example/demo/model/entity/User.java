package com.example.demo.model.entity;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	private Long userId;

	/** The name. */
	@Column(name = "name")
	private String name;

	/** The email. */
	@Column(name = "email")
	private String email;

	/** The canvas user id. */
	@Column(name = "canvas_user_id")
	private Long canvasUserId;

	/** The group id. */
	@Column(name = "group_id")
	private Long groupId;

	/** The project preferences. */
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private Set<ProjectPreference> projectPreferences;

	/** The role. */
	@Enumerated(EnumType.STRING)
	private Role role;

	/** The password. */
	@Column(name = "password_temporary")
	private String passwordTemporary;

	@Column(name = "password")
	private String password;

	/** The proposed projects. */
	@JsonBackReference
	@OneToMany(mappedBy = "owner")
	private Set<Project> proposedProjects;

	/** The group preference owner. */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_preference_owner_id", referencedColumnName = "groupPreferenceId")
	private GroupPreference groupPreference;

	/** The preference of team mates. */
	@ManyToOne
	@JoinColumn(name = "group_preference_id")
	private GroupPreference preference;

	public String getPasswordTemporary() {
		return passwordTemporary;
	}

	public void setPasswordTemporary(String passwordTemporary) {
		this.passwordTemporary = passwordTemporary;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

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

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the project preferences.
	 *
	 * @return the project preferences
	 */
	public Set<ProjectPreference> getProjectPreferences() {
		return projectPreferences;
	}

	/**
	 * Sets the project preferences.
	 *
	 * @param projectPreferences the new project preferences
	 */
	public void setProjectPreferences(Set<ProjectPreference> projectPreferences) {
		this.projectPreferences = projectPreferences;
	}

	/**
	 * Gets the proposed projects.
	 *
	 * @return the proposed projects
	 */
	public Set<Project> getProposedProjects() {
		return proposedProjects;
	}

	/**
	 * Sets the proposed projects.
	 *
	 * @param proposedProjects the new proposed projects
	 */
	public void setProposedProjects(Set<Project> proposedProjects) {
		this.proposedProjects = proposedProjects;
	}

	/**
	 * Gets the group preference.
	 *
	 * @return the group preference
	 */
	public GroupPreference getGroupPreferenceOwner() {
		return groupPreference;
	}

	/**
	 * Sets the group preference.
	 *
	 * @param groupPreference the new group preference
	 */
	public void setGroupPreferenceOwner(GroupPreference groupPreference) {
		this.groupPreference = groupPreference;
	}

	/**
	 * Gets the preference.
	 *
	 * @return the preference
	 */
	public GroupPreference getPreferenceMate() {
		return preference;
	}

	/**
	 * Sets the preference.
	 *
	 * @param preference the new preference
	 */
	public void setPreferenceMate(GroupPreference preference) {
		this.preference = preference;
	}
}
