package com.example.demo.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.service.EmailingService;
import com.example.demo.service.FormService;
import com.example.demo.service.UserService;


@WebMvcTest(FormController.class)
public class FormControllerTests {
    @MockBean
    private EmailingService emailingService;

    @MockBean
    private UserService userService;

    @MockBean
    private FormService formService;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void openFormTest() throws Exception {
        ArrayList<UserResponseDto> users = new ArrayList<UserResponseDto>();
        UserResponseDto user = new UserResponseDto();
        user.setEmail("test@exemple.com");
        user.setCanvasUserId(123456789L);
        user.setGroupId(123456789L);
        user.setName("testBoy");
        user.setPassword("P@$Sw0Rd");
        user.setPreferenceId(456789L);
        user.setRole(Role.STUDENT);
        user.setUserId(789L);
        users.add(user);
        when(userService.getAllUsers()).thenReturn(users);
        

        mvc.perform(post("/form/open").param("duration", "1").param("email", "20"))
            .andExpect(status().isOk())
            .andExpect(content().string("Student form is open, and all the students were notified!"));
    }

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void closeFormTest() throws Exception {
        mvc.perform(get("/form/close"))
            .andExpect(status().isOk())
            .andExpect(content().string("Form was closed manualy!"));
    }
    
    @Test
    @WithMockUser(authorities = {"STUDENT"})
    public void closeFormTestInsufficientAuthorization() throws Exception {
        mvc.perform(get("/form/close"))
            .andExpect(status().isForbidden());
        }

    @Test
    public void getFormStatusTest() throws Exception {
        when(formService.isFormOpen()).thenReturn(false);
        mvc.perform(get("/form/status"))
            .andExpect(status().isOk())
            .andExpect(content().string("Closed"));

        when(formService.isFormOpen()).thenReturn(true);
        mvc.perform(get("/form/status"))
            .andExpect(status().isOk())
            .andExpect(content().string("Open"));

    }
}
