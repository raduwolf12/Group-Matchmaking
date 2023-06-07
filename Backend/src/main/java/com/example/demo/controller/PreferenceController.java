package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/preferences")
public class PreferenceController {

	/** The preference service. */
	@Autowired
	ProjectPreferenceService projectPreferenceService;

	/** The group preference service. */
	@Autowired
	GroupPreferenceService groupPreferenceService;

	/**
	 * Sets the project preferences.
	 *
	 * @param preferences the preferences
	 * @return the response entity
	 * @throws ProjectNotFoundException the project not found exception
	 * @throws UserNotFoundException    the user not found exception
	 */
	@PostMapping("/save/project/preference")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
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

	/**
	 * Gets the project preferences.
	 *
	 * @param id the id
	 * @return the project preferences
	 * @throws UserNotFoundException    the user not found exception
	 * @throws GroupPreferenceException the group preference exception
	 */
	@GetMapping("/get/project/preference/{id}")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
	public ResponseEntity<List<ProjectPreferenceResponseDto>> getProjectPreferences(@PathVariable Long id)
			throws UserNotFoundException, GroupPreferenceException {

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		try {
			preferenceResponseDtos = projectPreferenceService.getPreferences(id);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());

		} catch (GroupPreferenceException e) {
			throw new GroupPreferenceException(e.getMessage());
		}

		return new ResponseEntity<List<ProjectPreferenceResponseDto>>(preferenceResponseDtos, HttpStatus.ACCEPTED);

	}

	/**
	 * Sets the group preferences.
	 *
	 * @param preferences the preferences
	 * @return the response entity
	 * @throws UserNotFoundException the user not found exception
	 */
	@PostMapping("/save/group/preference")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
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

	/**
	 * Gets the group preferences by user id.
	 *
	 * @param userId the user id
	 * @return the group preferences by user id
	 * @throws UserNotFoundException    the user not found exception
	 * @throws GroupPreferenceException the group preference exception
	 */
	@GetMapping("/get/group/preference/{userId}")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
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

	/**
	 * Leave pair.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@PutMapping("/{userId}/leave")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR')")
	public ResponseEntity<String> leavePair(@PathVariable("userId") Long userId) {
		try {
			groupPreferenceService.leavePair(userId);
			return ResponseEntity.ok("User successfully left the pair.");
		} catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
