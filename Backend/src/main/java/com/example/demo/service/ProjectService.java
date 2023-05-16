package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ProjectRequestDto;
import com.example.demo.model.dto.ProjectResponseDto;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Interface ProjectService.
 */
public interface ProjectService {

	/**
	 * Gets the project.
	 *
	 * @param id the id
	 * @return the project
	 * @throws ProjectNotFoundException 
	 */
	ProjectResponseDto getProject(Long id) throws ProjectNotFoundException;

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	List<ProjectResponseDto> getAllProjects();

	/**
	 * Delete project by id.
	 *
	 * @param id the id
	 * @throws ProjectNotFoundException 
	 */
	void deleteProjectById(Long id) throws ProjectNotFoundException;

	/**
	 * Creates the project.
	 *
	 * @param projectRequestDto the project request dto
	 * @param userId 
	 * @return the project response dto
	 * @throws UserNotFoundException 
	 */
	ProjectResponseDto createProject(@Valid ProjectRequestDto projectRequestDto) throws UserNotFoundException;

	/**
	 * Update project.
	 *
	 * @param projectRequestDto the project request dto
	 * @return the project response dto
	 * @throws ProjectNotFoundException 
	 */
	ProjectResponseDto updateProject(@Valid ProjectRequestDto projectRequestDto) throws ProjectNotFoundException;

}
