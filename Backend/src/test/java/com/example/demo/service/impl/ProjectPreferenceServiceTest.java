package com.example.demo.service.impl;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.ProjectPreference;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ProjectPreferenceRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectPreferenceService;
import com.example.demo.validation.exception.GroupPreferenceException;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

@SpringBootTest
public class ProjectPreferenceServiceTest {
	@Autowired
	private ProjectPreferenceService projectPreferenceService;

	@MockBean
	private ProjectRepository projectRepository;

	@MockBean
	private ProjectPreferenceRepository preferenceRepository;

	@Test
	public void createProjectPreferenceTest() {
		// TODO : implement this test (seems pretty hard to do)
	}

	@Test
	public void getPreferenceTest() throws ProjectNotFoundException {
		Project test = new Project();
		test.setProjectId(1L);

		when(projectRepository.findById(1L)).thenReturn(Optional.of(test));

		User testUser = new User();
		testUser.setCanvasUserId(1L);

		ProjectPreference testPref = new ProjectPreference();
		testPref.setUser(testUser);
		testPref.setProject(test);

		when(preferenceRepository.getPreferencesByUserId(1L)).thenReturn(List.of(testPref));

		List<ProjectPreferenceResponseDto> pref = null;
		try {
			pref = projectPreferenceService.getPreferences(1L);
		} catch (UserNotFoundException | GroupPreferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert (pref.size() == 1);
		// TODO : might be possible to test more here
	}
}
