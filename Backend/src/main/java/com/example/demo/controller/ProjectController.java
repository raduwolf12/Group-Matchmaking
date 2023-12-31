package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.ProjectRequestDto;
import com.example.demo.model.dto.ProjectResponseDto;
import com.example.demo.service.ProjectService;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class ProjectController.
 * 
 * @author Radu
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {

	/** The project service. */
	@Autowired
	ProjectService projectService;

	/**
	 * Creates the project.
	 *
	 * @param projectRequestDto the project request dto
	 * @return the response entity
	 */
	@PostMapping("/save/project")
	@PreAuthorize("hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<ProjectResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto) {

		ProjectResponseDto projectResponseDto;
		try {
			projectResponseDto = projectService.createProject(projectRequestDto);
			return new ResponseEntity<ProjectResponseDto>(projectResponseDto, HttpStatus.ACCEPTED);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Update project.
	 *
	 * @param projectRequestDto the project request dto
	 * @return the response entity
	 */
	@PutMapping("/update/project")
	@PreAuthorize("hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<ProjectResponseDto> updateProject(@Valid @RequestBody ProjectRequestDto projectRequestDto) {

		try {
			ProjectResponseDto projectResponseDto = projectService.updateProject(projectRequestDto);

			return new ResponseEntity<>(projectResponseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the project.
	 *
	 * @param id the id
	 * @return the project
	 * @throws ProjectNotFoundException the project not found exception
	 */
	@GetMapping("/get/project/{id}")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) throws ProjectNotFoundException {

		ProjectResponseDto project = new ProjectResponseDto();
		try {
			project = projectService.getProject(id);

		} catch (Exception e) {
			throw new ProjectNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@GetMapping("/get/projects/{userId}")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<List<ProjectResponseDto>> getProjectByUserId(@PathVariable Long userId) throws UserNotFoundException {

		List<ProjectResponseDto> projects = new ArrayList<ProjectResponseDto>();
		try {
			projects = projectService.getProjectsByUserId(userId);

		} catch (Exception e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}
	

	/**
	 * Gets the all projects.
	 *
	 * @return the all projects
	 */
	@GetMapping("/get/projects")
	@PreAuthorize("hasAuthority ('STUDENT') or hasAuthority ('PROFESSOR') or hasAuthority ('TEACHER')")
	public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {

		try {
			List<ProjectResponseDto> projects = projectService.getAllProjects();

			if (projects.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(projects, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Delete project.
	 *
	 * @param id the id
	 * @return the response entity
	 * @throws ProjectNotFoundException the project not found exception
	 */
	@DeleteMapping("/delete/project/{id}")
	@PreAuthorize("hasAuthority ('PROFESSOR')")
	public ResponseEntity<ProjectResponseDto> deleteProject(@PathVariable Long id) throws ProjectNotFoundException {

		try {
			projectService.deleteProjectById(id);

		} catch (Exception e) {
			throw new ProjectNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
