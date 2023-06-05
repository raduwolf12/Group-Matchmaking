package com.example.demo.model.entity;

import java.util.List;
import java.util.Set;

public class FinalGroup {

	Long id;
	Set<User> members;
	Project project;
	int soloSlots;
	int pairSlots;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getSoloSlots() {
		return soloSlots;
	}

	public void setSoloSlots(int soloSlots) {
		this.soloSlots = soloSlots;
	}

	public int getPairSlots() {
		return pairSlots;
	}

	public void setPairSlots(int pairSlots) {
		this.pairSlots = pairSlots;
	}

	@Override
	public String toString() {
		String  message = "Group: "+id+"\n Project:"+getProject().getTitle()+"\n Members:\n";
		if(getMembers()!=null)
			for(User user:getMembers())
				message = message +"\n " + user.getName();
		return  message+"\n ";
	}
	

}
