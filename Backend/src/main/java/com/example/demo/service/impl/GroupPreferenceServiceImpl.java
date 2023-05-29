package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.GroupPreferenceRequestDto;
import com.example.demo.model.dto.GroupPreferenceResponseDto;
import com.example.demo.model.entity.GroupPreference;
import com.example.demo.model.entity.User;
import com.example.demo.repository.GroupPreferenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupPreferenceService;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.UserNotFoundException;

@Service
public class GroupPreferenceServiceImpl implements GroupPreferenceService {

	@Autowired
	GroupPreferenceRepository groupPreferenceRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public GroupPreferenceResponseDto setPreferences(GroupPreferenceRequestDto preferences)
			throws UserNotFoundException {

		GroupPreference groupPreference = new GroupPreference();

		Optional<User> groupOwnerOptional = userRepository.findById(preferences.getGroupOwner());

		if (groupOwnerOptional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + preferences.getGroupOwner());

		User groupOwner = groupOwnerOptional.get();
		groupOwner.setGroupPreferenceOwner(groupPreference);
		groupOwner.setPreferenceMate(groupPreference);

		groupPreference.setGroupOwner(groupOwner);

		Set<User> mates = new HashSet<User>();
		for (Long teamMateId : preferences.getMates()) {

			Optional<User> teamMateOptional = userRepository.findById(teamMateId);

			if (teamMateOptional.isEmpty())
				throw new UserNotFoundException("User doesn't exist for the Id: " + teamMateId);

			User teamMate = teamMateOptional.get();
			teamMate.setGroupPreferenceOwner(groupPreference);
			teamMate.setPreferenceMate(groupPreference);
			mates.add(teamMate);
		}
		groupPreference.setMates(mates);

		groupPreferenceRepository.save(groupPreference);

		GroupPreferenceResponseDto groupPreferenceResponseDto = new GroupPreferenceResponseDto();
		BeanUtils.copyProperties(groupPreference, groupPreferenceResponseDto);
		groupPreferenceResponseDto.setGroupOwner(preferences.getGroupOwner());
		groupPreferenceResponseDto.setMates(preferences.getMates());

		return groupPreferenceResponseDto;
	}

	@Override
	public GroupPreferenceResponseDto getPreferences(Long id) throws UserNotFoundException, GroupPreferenceException {
		Optional<User> userOptional = userRepository.findById(id);

		if (userOptional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + id);

		User user = userOptional.get();
		
		if(user.getGroupPreferenceOwner()==null) {
			throw new GroupPreferenceException("No group preference found!");
		}

		Long ownerId = user.getGroupPreferenceOwner().getGroupPreferenceId();

		GroupPreference groupPreference = groupPreferenceRepository.findById(ownerId).get();

		GroupPreferenceResponseDto dto = new GroupPreferenceResponseDto();
		dto.setGroupOwner(groupPreference.getGroupOwner().getUserId());
		dto.setGroupPreferenceId(groupPreference.getGroupPreferenceId());

		Set<Long> mates = new HashSet<Long>();
		for (User teamMate : groupPreference.getMates()) {
			mates.add(teamMate.getUserId());
		}
		dto.setMates(mates);

		return dto;
	}

}
