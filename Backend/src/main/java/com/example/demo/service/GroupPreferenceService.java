package com.example.demo.service;

import com.example.demo.model.dto.GroupPreferenceRequestDto;
import com.example.demo.model.dto.GroupPreferenceResponseDto;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.UserNotFoundException;

/**
 * The Interface GroupPreferenceService.
 */
public interface GroupPreferenceService {

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the preferences
	 * @return the group preference response dto
	 * @throws UserNotFoundException the user not found exception
	 */
	GroupPreferenceResponseDto setPreferences(GroupPreferenceRequestDto preferences) throws UserNotFoundException;

	/**
	 * Gets the preferences.
	 *
	 * @param id the id
	 * @return the preferences
	 * @throws UserNotFoundException the user not found exception
	 * @throws GroupPreferenceException the group preference exception
	 */
	GroupPreferenceResponseDto getPreferences(Long id) throws UserNotFoundException, GroupPreferenceException;

	/**
	 * Leave pair.
	 *
	 * @param userId the user id
	 * @throws UserNotFoundException the user not found exception
	 */
	void leavePair(Long userId) throws UserNotFoundException;

}
