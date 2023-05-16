package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ProjectRequestDto;
import com.example.demo.model.dto.ProjectResponseDto;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public ProjectResponseDto getProject(Long id) throws ProjectNotFoundException {
		Optional<Project> optional = projectRepository.findById(id);

		if (optional.isEmpty())
			throw new ProjectNotFoundException("Project doesn't exist for the Id: " + id);

		Project project = optional.get();

		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		BeanUtils.copyProperties(project, projectResponseDto);

		return projectResponseDto;
	}

	@Override
	public List<ProjectResponseDto> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		List<ProjectResponseDto> projectResponseDtos = new ArrayList<ProjectResponseDto>();
		for (Project project : projects) {
			ProjectResponseDto projectResponseDto = new ProjectResponseDto();
			BeanUtils.copyProperties(project, projectResponseDto);
			projectResponseDtos.add(projectResponseDto);
		}
		return projectResponseDtos;
	}

	@Override
	public void deleteProjectById(Long id) throws ProjectNotFoundException {
		Optional<Project> optional = projectRepository.findById(id);

		if (optional.isEmpty())
			throw new ProjectNotFoundException("Project doesn't exist for the Id: " + id);

		Project project = optional.get();

		projectRepository.delete(project);
	}

	@Override
	public ProjectResponseDto createProject(@Valid ProjectRequestDto projectRequestDto) throws UserNotFoundException {
		Project project = new Project();
		BeanUtils.copyProperties(projectRequestDto, project);
		Long userId = projectRequestDto.getOwner_user_id();

		Optional<User> optional = userRepository.findById(userId);

		if (optional.isEmpty())
			throw new UserNotFoundException("User doesn't exist for the Id: " + userId);
		project.setOwner(optional.get());

		projectRepository.save(project);

		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		BeanUtils.copyProperties(project, projectResponseDto);
		projectResponseDto.setOwner_user_id(userId);

		return projectResponseDto;
	}

	@Override
	public ProjectResponseDto updateProject(@Valid ProjectRequestDto projectRequestDto)
			throws ProjectNotFoundException {
		Optional<Project> optional = projectRepository.findById(projectRequestDto.getProjectId());

		if (optional.isEmpty())
			throw new ProjectNotFoundException("Project doesn't exist for the Id: " + projectRequestDto.getProjectId());

		Project project = optional.get();
		BeanUtils.copyProperties(projectRequestDto, project);

		projectRepository.save(project);

		ProjectResponseDto projectResponseDto = new ProjectResponseDto();
		BeanUtils.copyProperties(project, projectResponseDto);

		return projectResponseDto;
	}
}
