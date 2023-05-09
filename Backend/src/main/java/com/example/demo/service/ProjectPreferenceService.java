package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;


public interface ProjectPreferenceService {

	List<ProjectPreferenceResponseDto> setPreferences(@Valid List<ProjectPreferenceRequestDto> preferences)
			throws UserNotFoundException, ProjectNotFoundException;

	List<ProjectPreferenceResponseDto> getPreferences(Long id) throws ProjectNotFoundException;

}
