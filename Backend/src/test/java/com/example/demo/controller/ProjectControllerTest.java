package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;

import com.example.demo.model.dto.ProjectResponseDto;
import com.example.demo.service.ProjectService;
import com.example.demo.validation.exception.ProjectNotFoundException;

@WebMvcTest(ProjectController.class)
@ExtendWith(SpringExtension.class)
public class ProjectControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    ProjectService projectService;

    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void getProjectsTestEmpty() throws Exception {
        when(projectService.getAllProjects()).thenReturn(new ArrayList<>());

        mvc.perform(get("/projects/get/projects"))
            .andExpect(status().isNoContent())
            .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void getProjectsTest() throws Exception {
        ArrayList<ProjectResponseDto> projects = new ArrayList<ProjectResponseDto>();
        ProjectResponseDto project = new ProjectResponseDto();
        project.setProjectId(1L);
        project.setDescription("bla bla bla");
        project.setOwner_user_id(123456789L);
        project.setSize(3L);
        project.setTitle("exemple");
        projects.add(project);
        
        when(projectService.getAllProjects()).thenReturn(projects);

        mvc.perform(get("/projects/get/projects"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{\"projectId\":1,\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}]"));
    }

    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void getProjectByIdTestNonExistent() throws Exception {
        when(projectService.getProject(1L)).thenThrow(new ProjectNotFoundException("test"));

        mvc.perform(get("/projects/get/project/1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid data test\"}"));
    }

    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void getProjectByIdTest() throws Exception {
        ProjectResponseDto project = new ProjectResponseDto();
        project.setProjectId(1L);
        project.setDescription("bla bla bla");
        project.setOwner_user_id(123456789L);
        project.setSize(3L);
        project.setTitle("exemple");
        
        when(projectService.getProject(1L)).thenReturn(project);

        mvc.perform(get("/projects/get/project/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"projectId\":1,\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}"));
    }

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void deleteProjectTestNonExistent() throws Exception {
        doThrow(new ProjectNotFoundException("test")).when(projectService).deleteProjectById(1L);

        mvc.perform(delete("/projects/delete/project/1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().json("{\"message\":\"Invalid data test\"}"));
    }

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void deleteProjectTest() throws Exception {
        doNothing().when(projectService).deleteProjectById(1L);

        mvc.perform(delete("/projects/delete/project/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(authorities = {"TEACHER"})
    public void createProjectTest() throws Exception {
        ProjectResponseDto project = new ProjectResponseDto();
        project.setProjectId(1L);
        project.setDescription("bla bla bla");
        project.setOwner_user_id(123456789L);
        project.setSize(3L);
        project.setTitle("exemple");
        
        when(projectService.createProject(any())).thenReturn(project);

        mvc.perform(post("/projects/save/project")
            .contentType("application/json")
            .content("{\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}"))
            .andExpect(status().isAccepted())
            .andExpect(content().json("{\"projectId\":1,\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}"));
    }

    @Test
    @WithMockUser(authorities = {"TEACHER"})
    public void updateProjectTest() throws Exception {
        ProjectResponseDto project = new ProjectResponseDto();
        project.setProjectId(1L);
        project.setDescription("bla bla bla");
        project.setOwner_user_id(123456789L);
        project.setSize(3L);
        project.setTitle("exemple");
        
        when(projectService.updateProject(any())).thenReturn(project);

        mvc.perform(put("/projects/update/project")
            .contentType("application/json")
            .content("{\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"projectId\":1,\"title\":\"exemple\",\"description\":\"bla bla bla\",\"size\":3,\"owner_user_id\":123456789}"));
    }
}
