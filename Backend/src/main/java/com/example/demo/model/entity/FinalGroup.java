package com.example.demo.model.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The Class FinalGroup.
 */
@Entity
@Table(name = "final_group")
public class FinalGroup {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	/** The members. */
	@ManyToMany
	@JoinTable(name = "final_group_user", joinColumns = @JoinColumn(name = "final_group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> members;

	@ManyToOne
	@JoinColumn(name = "project_id")
	Project project;

	@Column(name = "solo_slots")
	int soloSlots;

	@Column(name = "pair_slots")
	int pairSlots;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the members.
	 *
	 * @return the members
	 */
	public Set<User> getMembers() {
		return members;
	}

	/**
	 * Sets the members.
	 *
	 * @param members the new members
	 */
	public void setMembers(Set<User> members) {
		this.members = members;
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

	/**
	 * Gets the solo slots.
	 *
	 * @return the solo slots
	 */
	public int getSoloSlots() {
		return soloSlots;
	}

	/**
	 * Sets the solo slots.
	 *
	 * @param soloSlots the new solo slots
	 */
	public void setSoloSlots(int soloSlots) {
		this.soloSlots = soloSlots;
	}

	/**
	 * Gets the pair slots.
	 *
	 * @return the pair slots
	 */
	public int getPairSlots() {
		return pairSlots;
	}

	/**
	 * Sets the pair slots.
	 *
	 * @param pairSlots the new pair slots
	 */
	public void setPairSlots(int pairSlots) {
		this.pairSlots = pairSlots;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String message = "Group: " + id + "\n Project:" + getProject().getTitle() + "\n Members:\n";
		if (getMembers() != null)
			for (User user : getMembers())
				message = message + "\n " + user.getName();
		return message + "\n ";
	}

}
