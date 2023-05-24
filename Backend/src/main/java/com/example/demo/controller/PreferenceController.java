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

import com.example.demo.model.dto.GroupPreferenceRequestDto;
import com.example.demo.model.dto.GroupPreferenceResponseDto;
import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.service.GroupPreferenceService;
import com.example.demo.service.ProjectPreferenceService;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class ProjectPreferenceController.
 * 
 * @author Radu
 */
@RestController
@RequestMapping("/preferences")
public class PreferenceController {

	/** The preference service. */
	@Autowired
	ProjectPreferenceService projectPreferenceService;

	@Autowired
	GroupPreferenceService groupPreferenceService;

	@PostMapping("/save/project/preference")
	public ResponseEntity<List<ProjectPreferenceResponseDto>> setProjectPreferences(
			@Valid @RequestBody List<ProjectPreferenceRequestDto> preferences)
			throws ProjectNotFoundException, UserNotFoundException {

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		try {
			preferenceResponseDtos = projectPreferenceService.setPreferences(preferences);
		} catch (ProjectNotFoundException e) {
			throw new ProjectNotFoundException(e.getMessage());
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());

		}

		return new ResponseEntity<List<ProjectPreferenceResponseDto>>(preferenceResponseDtos, HttpStatus.ACCEPTED);

	}

	@GetMapping("/get/project/preference/{id}")
	public ResponseEntity<List<ProjectPreferenceResponseDto>> getProjectPreferences(@PathVariable Long id)
			throws ProjectNotFoundException {

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		try {
			preferenceResponseDtos = projectPreferenceService.getPreferences(id);
		} catch (ProjectNotFoundException e) {
			throw new ProjectNotFoundException(e.getMessage());
		}

		return new ResponseEntity<List<ProjectPreferenceResponseDto>>(preferenceResponseDtos, HttpStatus.ACCEPTED);

	}

	@PostMapping("/save/group/preference")
	public ResponseEntity<GroupPreferenceResponseDto> setGroupPreferences(
			@RequestBody GroupPreferenceRequestDto preferences) throws UserNotFoundException {

		GroupPreferenceResponseDto responseDto = new GroupPreferenceResponseDto();
		try {
			responseDto = groupPreferenceService.setPreferences(preferences);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());

		}

		return new ResponseEntity<GroupPreferenceResponseDto>(responseDto, HttpStatus.ACCEPTED);

	}

	@GetMapping("/get/group/preference/{userId}")
	public ResponseEntity<GroupPreferenceResponseDto> getGroupPreferencesByUserId(@PathVariable Long userId)
			throws UserNotFoundException, GroupPreferenceException {

		GroupPreferenceResponseDto responseDto = new GroupPreferenceResponseDto();
		try {
			responseDto = groupPreferenceService.getPreferences(userId);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());

		} catch (GroupPreferenceException e) {
			throw new GroupPreferenceException(e.getMessage());
		}

		return new ResponseEntity<GroupPreferenceResponseDto>(responseDto, HttpStatus.ACCEPTED);

	}

}
