package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.ProjectPreference;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ProjectPreferenceRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectPreferenceService;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

import jakarta.validation.Valid;

/**
 * The Class ProjectPreferenceServiceImpl.
 */
@Service
public class ProjectPreferenceServiceImpl implements ProjectPreferenceService {

	/** The preference repository. */
	@Autowired
	ProjectPreferenceRepository preferenceRepository;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/** The project repository. */
	@Autowired
	ProjectRepository projectRepository;

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the preferences
	 * @return the list
	 * @throws UserNotFoundException the user not found exception
	 * @throws ProjectNotFoundException the project not found exception
	 */
	@Override
	public List<ProjectPreferenceResponseDto> setPreferences(@Valid List<ProjectPreferenceRequestDto> preferences)
			throws UserNotFoundException, ProjectNotFoundException {
		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();
		for (ProjectPreferenceRequestDto preferenceRequestDto : preferences) {
			ProjectPreference projectPreference = new ProjectPreference();
			BeanUtils.copyProperties(preferenceRequestDto, projectPreference);

			Optional<User> userOptional = userRepository.findById(preferenceRequestDto.getUserId());
			if (userOptional.isEmpty())
				throw new UserNotFoundException("User doesn't exist for the Id: " + preferenceRequestDto.getUserId());

			projectPreference.setUser(userOptional.get());

			Optional<Project> projectOptional = projectRepository.findById(preferenceRequestDto.getProjectId());
			if (projectOptional.isEmpty())
				throw new ProjectNotFoundException(
						"User doesn't exist for the Id: " + preferenceRequestDto.getProjectId());

			projectPreference.setProject(projectOptional.get());

			preferenceRepository.save(projectPreference);

			ProjectPreferenceResponseDto projectPreferenceResponseDto = new ProjectPreferenceResponseDto();
			BeanUtils.copyProperties(projectPreference, projectPreferenceResponseDto);

			projectPreferenceResponseDto.setProjectId(projectOptional.get().getProjectId());
			projectPreferenceResponseDto.setUserId(userOptional.get().getCanvasUserId());

			preferenceResponseDtos.add(projectPreferenceResponseDto);

		}
		return preferenceResponseDtos;
	}

	/**
	 * Gets the preferences.
	 *
	 * @param id the id
	 * @return the preferences
	 * @throws ProjectNotFoundException the project not found exception
	 */
	@Override
	public List<ProjectPreferenceResponseDto> getPreferences(Long id) throws ProjectNotFoundException {

		Optional<Project> projectOptional = projectRepository.findById(id);
		if (projectOptional.isEmpty())
			throw new ProjectNotFoundException("User doesn't exist for the Id: " + id);

		List<ProjectPreferenceResponseDto> preferenceResponseDtos = new ArrayList<ProjectPreferenceResponseDto>();

		List<ProjectPreference> preferences = preferenceRepository.getPreferencesByUserId(id);
		for (ProjectPreference preference : preferences) {
			ProjectPreferenceResponseDto projectPreferenceResponseDto = new ProjectPreferenceResponseDto();
			BeanUtils.copyProperties(preference, projectPreferenceResponseDto);

			projectPreferenceResponseDto.setProjectId(preference.getProject().getProjectId());
			projectPreferenceResponseDto.setUserId(preference.getUser().getCanvasUserId());

			preferenceResponseDtos.add(projectPreferenceResponseDto);
		}
		return preferenceResponseDtos;
	}

}
