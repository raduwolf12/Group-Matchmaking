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
	 * @throws ProjectNotFoundException the project not found exception
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
	 * @throws ProjectNotFoundException the project not found exception
	 */
	void deleteProjectById(Long id) throws ProjectNotFoundException;

	/**
	 * Creates the project.
	 *
	 * @param projectRequestDto the project request dto
	 * @return the project response dto
	 * @throws UserNotFoundException the user not found exception
	 */
	ProjectResponseDto createProject(@Valid ProjectRequestDto projectRequestDto) throws UserNotFoundException;

	/**
	 * Update project.
	 *
	 * @param projectRequestDto the project request dto
	 * @return the project response dto
	 * @throws ProjectNotFoundException the project not found exception
	 */
	ProjectResponseDto updateProject(@Valid ProjectRequestDto projectRequestDto) throws ProjectNotFoundException;

	/**
	 * Gets the projects by user id.
	 *
	 * @param userId the user id
	 * @return the projects by user id
	 * @throws UserNotFoundException the user not found exception
	 */
	List<ProjectResponseDto> getProjectsByUserId(Long userId) throws UserNotFoundException;

}
