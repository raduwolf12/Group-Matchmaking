package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Configuration;
import com.example.demo.model.entity.FinalGroup;
import com.example.demo.model.entity.PairPreference;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.ProjectPreference;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.repository.ConfigurationRepository;
import com.example.demo.repository.GroupPreferenceRepository;
import com.example.demo.repository.ProjectPreferenceRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.UserNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	GroupPreferenceRepository groupPreferenceRepository;

	@Autowired
	ProjectPreferenceRepository projectPreferenceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ConfigurationRepository configurationRepository;

	@Override
	public void formGroups() {
		List<User> students = getStudents();
		List<Project> projects = projectRepository.findAll();
		
		Configuration configuration = configurationRepository.findAll().get(0);
		
		int groupSize = configuration.getGroupSize();
		int pairSize = configuration.getPairSize();

		// Separate users into three categories
		List<User> pairedUsers = new ArrayList<>();
		List<User> unpairedUsers = new ArrayList<>();
		List<User> unspecifiedUsers = new ArrayList<>();

		for (User user : students) {
			if (user.getPairPreferences() != null && !user.getPairPreferences().isEmpty()) {
				if (user.getPairPreferences().get(0).getUsers().size() >= 2) {
					pairedUsers.add(user);
				} else if (user.getPairPreferences().get(0).getUsers().size() == 1) {
					unpairedUsers.add(user);
				}
			} else {
				unspecifiedUsers.add(user);
			}
		}
		Set<User> pairedUsersOwners = new LinkedHashSet<>();
		for (User user : pairedUsers) {
			PairPreference pairPreference = user.getPairPreferences().get(0);
			pairedUsersOwners.add(pairPreference.getGroupCreator());

		}

		// Shuffle the unpaired users to randomize the group formation
		Collections.shuffle(unpairedUsers);

		List<FinalGroup> groups = createEmptyGroups(projects, groupSize, pairSize);
		List<User> pairedUsersOwnersList = new ArrayList<User>(pairedUsersOwners);

		assignProjectsToPairedUsers(pairedUsersOwnersList, projects, groups);

		assignProjectsToSoloUsers(unpairedUsers, projects, groups);

		assignUnspecifiedUsers(unspecifiedUsers, groups);
		System.out.println(groups);

	}

	private List<User> getStudents() {
		List<User> users = userRepository.findAll();

		List<User> students = users.stream().filter(user -> user.getRole() == Role.STUDENT)
				.collect(Collectors.toList());

		return students;
	}

	private Project getHighestRankedProject(PairPreference pairPreference, List<Project> projects) {
		Long groupCreatorId = pairPreference.getGroupCreator().getUserId();
		List<ProjectPreference> projectPreferences = new ArrayList<ProjectPreference>();
		try {
			projectPreferences = getPreferences(groupCreatorId);
		} catch (UserNotFoundException | GroupPreferenceException e) {
			e.printStackTrace();
		}
		for (ProjectPreference preference : projectPreferences) {
			Project project = preference.getProject();
			if (projects.contains(project)) {
				return project;
			}
		}
		return null;
	}

	public List<ProjectPreference> getPreferences(Long id) throws UserNotFoundException, GroupPreferenceException {

		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + id);

		PairPreference pairPreferences = groupPreferenceRepository.findByUserId(userOptional.get().getUserId())
				.orElseThrow(() -> new GroupPreferenceException("GroupPreference not found for User Id: " + id));

		Long pairOwnerId = pairPreferences.getGroupCreator().getUserId();

		List<ProjectPreference> preferences = projectPreferenceRepository.getPreferencesByUserId(pairOwnerId);

		return preferences;
	}

	private void assignProjectsToPairedUsers(List<User> pairedUsers, List<Project> projects, List<FinalGroup> groups) {

		for (User user : pairedUsers) {
			PairPreference pairPreference = user.getPairPreferences().get(0);
			if (pairPreference != null) {
				Project project = getHighestRankedProject(pairPreference, projects);
				if (project != null) {
					int groupId = getGroupId(groups, project);
					Set<User> members = groups.get(groupId).getMembers();
					if (members == null) {
						members = new HashSet<User>();
					}
					for (User member : pairPreference.getUsers()) {
						members.add(member);
					}
					groups.get(groupId).setMembers(members);
					int pairSlot = groups.get(groupId).getPairSlots();
					pairSlot = pairSlot - 1;
					groups.get(groupId).setPairSlots(pairSlot);
					if (pairSlot == 0) {
						projects.remove(project);
					}
				}
			}
		}
	}

	private void assignProjectsToSoloUsers(List<User> soloUsers, List<Project> projects, List<FinalGroup> groups) {

		for (User user : soloUsers) {
			PairPreference pairPreference = user.getPairPreferences().get(0);
			if (pairPreference != null) {

				Project project = getHighestRankedProject(pairPreference, projects);

				if (project != null) {

					int groupId = getGroupId(groups, project);

					Set<User> members = groups.get(groupId).getMembers();

					if (members == null) {
						members = new HashSet<User>();
					}

					members.add(pairPreference.getUsers().get(0));

					groups.get(groupId).setMembers(members);
					int soloSlot = groups.get(groupId).getSoloSlots();
					soloSlot = soloSlot - 1;
					groups.get(groupId).setSoloSlots(soloSlot);
					if (soloSlot == 0) {
						projects.remove(project);
					}
				}
			}
		}
	}

	public void assignUnspecifiedUsers(List<User> unspecifiedUsers, List<FinalGroup> groups) {
		int numOfUsers = unspecifiedUsers.size();

		int groupIndex = 0;
		int userIndex = 0;

		while (userIndex < numOfUsers) {
			User user = unspecifiedUsers.get(userIndex);
			FinalGroup group = groups.get(groupIndex);

			Set<User> members = group.getMembers();
			if (members == null || members.isEmpty()) {
				groupIndex++;
				break;
			}
			members.add(user);
			group.setMembers(members);
			userIndex++;
			groupIndex++;
			if (groupIndex >= groups.size()) {
				groupIndex = 0;
			}

		}

	}

	private int getGroupId(List<FinalGroup> groups, Project project) {
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).getProject().equals(project)) {
				return i;
			}
		}
		return -1;
	}

	private int calculateSoloSlots(int teamSize, int pairSize) {
		int soloSlots = teamSize % pairSize;
		if (soloSlots == 0)
			return pairSize;
		return soloSlots;
	}

	private int calculatePairSlots(int teamSize, int pairSize) {
		int size = teamSize / pairSize;
		int soloSlots = teamSize % pairSize;
		if (soloSlots == 0)
			return size - 1;
		return size;
	}

	private boolean checkProjectAvailability(FinalGroup group, int teamSize, Project project) {
		return numberOfEmptySlots(group, teamSize) > 0;
	}

	private int numberOfEmptySlots(FinalGroup group, int teamSize) {
		return teamSize - group.getMembers().size();
	}

	private List<FinalGroup> createEmptyGroups(List<Project> list, int groupSize, int pairSize) {
		List<FinalGroup> groups = new ArrayList<FinalGroup>();
		for (Project project : list) {
			FinalGroup group = new FinalGroup();
			group.setProject(project);

			group.setPairSlots(calculatePairSlots(groupSize, pairSize));
			group.setSoloSlots(calculateSoloSlots(groupSize, pairSize));

			groups.add(group);
		}
		return groups;
	}

}