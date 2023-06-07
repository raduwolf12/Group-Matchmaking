package com.example.demo.model.entity;

import java.util.Set;

/**
 * The Class FinalGroup.
 */
public class FinalGroup {

	/** The id. */
	Long id;
	
	/** The members. */
	Set<User> members;
	
	/** The project. */
	Project project;
	
	/** The solo slots. */
	int soloSlots;
	
	/** The pair slots. */
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
