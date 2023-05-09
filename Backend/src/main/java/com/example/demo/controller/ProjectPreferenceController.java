package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.service.ProjectPreferenceService;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class ProjectPreferenceController.
 */
@RestController
@RequestMapping("/preferences")
public class ProjectPreferenceController {

	/** The preference service. */
	@Autowired
	ProjectPreferenceService preferenceService;

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the preferences
	 * @return the response entity
	 * @throws ProjectNotFoundException the project not found exception
	 * @throws UserNotFoundException the user not found exception
	 */
	@PostMapping("/save/preference")
	public ResponseEntity<List<ProjectPreferenceResponseDto>> setPreferences(
			@Valid @RequestBody List<ProjectPreferenceRequestDto> preferences)
			throws ProjectNotFoundException, UserNotFoundException {

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		try {
			preferenceResponseDtos = preferenceService.setPreferences(preferences);
		} catch (ProjectNotFoundException e) {
			throw new ProjectNotFoundException(e.getMessage());
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());

		}

		return new ResponseEntity<List<ProjectPreferenceResponseDto>>(preferenceResponseDtos, HttpStatus.ACCEPTED);

	}

	/**
	 * Gets the preferences.
	 *
	 * @param id the id
	 * @return the preferences
	 * @throws ProjectNotFoundException the project not found exception
	 */
	@GetMapping("/get/preference/{id}")
	public ResponseEntity<List<ProjectPreferenceResponseDto>> getPreferences(@PathVariable Long id)
			throws ProjectNotFoundException {

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		try {
			preferenceResponseDtos = preferenceService.getPreferences(id);
		} catch (ProjectNotFoundException e) {
			throw new ProjectNotFoundException(e.getMessage());
		}

		return new ResponseEntity<List<ProjectPreferenceResponseDto>>(preferenceResponseDtos, HttpStatus.ACCEPTED);

	}

}
