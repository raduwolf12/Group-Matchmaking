package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Interface ProjectPreferenceService.
 */
public interface ProjectPreferenceService {

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the preferences
	 * @return the list
	 * @throws UserNotFoundException the user not found exception
	 * @throws ProjectNotFoundException the project not found exception
	 */
	List<ProjectPreferenceResponseDto> setPreferences(@Valid List<ProjectPreferenceRequestDto> preferences)
			throws UserNotFoundException, ProjectNotFoundException;

	/**
	 * Gets the preferences.
	 *
	 * @param id the id
	 * @return the preferences
	 * @throws ProjectNotFoundException the project not found exception
	 * @throws UserNotFoundException 
	 * @throws GroupPreferenceException 
	 */
	List<ProjectPreferenceResponseDto> getPreferences(Long id) throws UserNotFoundException, GroupPreferenceException;

}
