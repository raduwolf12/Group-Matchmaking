package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.GroupPreferenceRequestDto;
import com.example.demo.model.dto.GroupPreferenceResponseDto;
import com.example.demo.model.entity.PairPreference;
import com.example.demo.model.entity.User;
import com.example.demo.repository.GroupPreferenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupPreferenceService;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.UserNotFoundException;

/**
 * The Class GroupPreferenceServiceImpl.
 */
@Service
public class GroupPreferenceServiceImpl implements GroupPreferenceService {

	/** The group preference repository. */
	@Autowired
	GroupPreferenceRepository groupPreferenceRepository;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the preferences
	 * @return the group preference response dto
	 * @throws UserNotFoundException the user not found exception
	 */
	@Override
	public GroupPreferenceResponseDto setPreferences(GroupPreferenceRequestDto preferences)
			throws UserNotFoundException {

		PairPreference groupPreference = new PairPreference();

		Optional<User> groupOwnerOptional = userRepository.findById(preferences.getGroupOwner());

		if (groupOwnerOptional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + preferences.getGroupOwner());

		List<User> mates = userRepository.findAllById(preferences.getMates());
		if (mates.size() != preferences.getMates().size()) {
			throw new UserNotFoundException("One or more users don't exist");
		}

		groupPreference.setGroupCreator(groupOwnerOptional.get());
		List<User> users = groupPreference.getUsers();
		if (users != null) {
			users.addAll(mates);
		} else {
			users = mates;
		}

		groupPreference.setUsers(users);

		groupPreferenceRepository.save(groupPreference);

		GroupPreferenceResponseDto groupPreferenceResponseDto = new GroupPreferenceResponseDto();
		BeanUtils.copyProperties(groupPreference, groupPreferenceResponseDto);
		groupPreferenceResponseDto.setGroupOwner(preferences.getGroupOwner());
		groupPreferenceResponseDto.setMates(preferences.getMates());

		return groupPreferenceResponseDto;
	}

	/**
	 * Gets the preferences.
	 *
	 * @param userId the user id
	 * @return the preferences
	 * @throws UserNotFoundException the user not found exception
	 * @throws GroupPreferenceException the group preference exception
	 */
	@Override
	public GroupPreferenceResponseDto getPreferences(Long userId)
			throws UserNotFoundException, GroupPreferenceException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User doesn't exist for the Id: " + userId));

		PairPreference pairPreferences = groupPreferenceRepository.findByUserId(user.getUserId())
				.orElseThrow(() -> new GroupPreferenceException("GroupPreference not found for User Id: " + userId));

		GroupPreferenceResponseDto responseDto = new GroupPreferenceResponseDto();
		responseDto.setGroupPreferenceId(pairPreferences.getGroupPreferenceId());
		responseDto.setGroupOwner(pairPreferences.getGroupCreator().getUserId());

		Set<Long> mateIds = new HashSet<>();
		for (User mate : pairPreferences.getUsers()) {
			mateIds.add(mate.getUserId());
		}
		responseDto.setMates(mateIds);

		return responseDto;

	}

}
