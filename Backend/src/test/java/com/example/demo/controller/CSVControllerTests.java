package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.pojo.CSVHelper;
import com.example.demo.service.CSVService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CSVController.class)
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CSVControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    CSVService fileService;

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void uploadCSVTest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "students.csv", "text/csv", "Good file".getBytes());

        mvc.perform(multipart(HttpMethod.POST, "/api/csv/upload").file(file))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"message\":\"Uploaded the file successfully: students.csv\"}"));
    }

    @Test
    @WithMockUser(authorities = {"PROFESSOR"})
    public void uploadCSVTestWithWrongFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "students.csv", MediaType.IMAGE_PNG_VALUE, "wrong file".getBytes());

        mvc.perform(multipart(HttpMethod.POST, "/api/csv/upload").file(file))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("{\"message\":\"Please upload a csv file!\"}"));
    }

    @Test
    @WithMockUser(authorities = {"TEACHER"})
    public void uploadCSVTestWithoutPermission() throws Exception {
        mvc.perform(post("/api/csv/upload"))
            .andExpect(status().isForbidden());
    }
}
