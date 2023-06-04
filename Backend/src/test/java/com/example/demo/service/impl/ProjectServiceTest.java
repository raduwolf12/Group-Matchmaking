package com.example.demo.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.dto.ProjectRequestDto;
import com.example.demo.model.dto.ProjectResponseDto;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ProjectPreferenceRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProjectPreferenceService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserDetailsService;
import com.example.demo.validation.exception.ProjectNotFoundException;
import com.example.demo.validation.exception.UserNotFoundException;

@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @MockBean
    ProjectRepository projectRepository;

    @MockBean
    UserRepository userRepository;
    
    @MockBean   // IDK the fuck why it is needed but otherwize it's impossible to initialize the test environement
    private ProjectPreferenceService projectPreferenceService;

    @MockBean
    private ProjectPreferenceRepository preferenceRepository;

    @Test
    public void getProjectTest() throws ProjectNotFoundException {
        Project test = new Project();
        test.setProjectId(1L);
        
        when(projectRepository.findById(1L)).thenReturn(Optional.of(test));

        ProjectResponseDto project = projectService.getProject(1L);
        assert(project.getProjectId() == 1L);
    }

    @Test
    public void getProjectTestFail() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            projectService.getProject(1L);
            assert(false);
        } catch (ProjectNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void getAllProjectsTest() {
        Project test = new Project();
        test.setProjectId(1L);
        
        when(projectRepository.findAll()).thenReturn(List.of(test));

        List<ProjectResponseDto> projects = projectService.getAllProjects();
        assert(projects.size() == 1);
        assert(projects.get(0).getProjectId() == 1L);
    }

    @Test
    public void deleteProjectByIdTest() throws ProjectNotFoundException {
        Project test = new Project();
        test.setProjectId(1L);
        
        when(projectRepository.findById(1L)).thenReturn(Optional.of(test));

        projectService.deleteProjectById(1L);

        verify(projectRepository).delete(test);
    }

    @Test
    public void deleteProjectByIdTestFail() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            projectService.deleteProjectById(1L);
            assert(false);
        } catch (ProjectNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void createProjectTest() throws UserNotFoundException {
        ProjectRequestDto test = new ProjectRequestDto();
        test.setProjectId(1L);
        test.setOwner_user_id(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));

        ProjectResponseDto project = projectService.createProject(test);
        assert(project.getProjectId() == 1L);
    }

    @Test
    public void createProjectTestFail() {
        ProjectRequestDto test = new ProjectRequestDto();
        test.setProjectId(1L);
        test.setOwner_user_id(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            projectService.createProject(test);
            assert(false);
        } catch (UserNotFoundException e) {
            assert(true);
        }
    }

    @Test
    public void updateProjectTest() throws ProjectNotFoundException {
        Project test = new Project();
        test.setProjectId(1L);
        
        when(projectRepository.findById(1L)).thenReturn(Optional.of(test));

        ProjectRequestDto testRequest = new ProjectRequestDto();
        testRequest.setProjectId(1L);
        testRequest.setOwner_user_id(1L);

        projectService.updateProject(testRequest);

        verify(projectRepository).save(test);
    }

    @Test
    public void updateProjectTestFail() {
        ProjectRequestDto testRequest = new ProjectRequestDto();
        testRequest.setProjectId(1L);
        testRequest.setOwner_user_id(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            projectService.updateProject(testRequest);
            assert(false);
        } catch (ProjectNotFoundException e) {
            assert(true);
        }
    }
}
