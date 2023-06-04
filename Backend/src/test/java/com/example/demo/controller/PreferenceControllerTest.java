package com.example.demo.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.dto.ProjectPreferenceRequestDto;
import com.example.demo.model.dto.ProjectPreferenceResponseDto;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.ProjectPreference;
import com.example.demo.service.GroupPreferenceService;
import com.example.demo.service.ProjectPreferenceService;


@WebMvcTest(PreferenceControllerTest.class)
@ExtendWith(SpringExtension.class)
public class PreferenceControllerTest {
    @MockBean
    ProjectPreferenceService projectPreferenceService;

    @MockBean
    GroupPreferenceService groupPreferenceService;

    @Autowired
    private MockMvc mvc;

    // TODO: Fix this test (I don't know how to send the data in the request body)
    // @Test
    // public void setProjectPreferencesTest() throws Exception {
    //     ProjectPreferenceRequestDto pref = new ProjectPreferenceRequestDto();
    //     pref.setProjectId(1L);
    //     pref.setUserId(1L);
    //     pref.setRank(1);
    //     pref.setProjectPreferenceId(1L);

    //     ArrayList<ProjectPreferenceRequestDto> prefList = new ArrayList<ProjectPreferenceRequestDto>();
    //     prefList.add(pref);

    //     ProjectPreferenceResponseDto prefResponse = new ProjectPreferenceResponseDto();
    //     prefResponse.setProjectId(1L);
    //     prefResponse.setUserId(1L);
    //     prefResponse.setRank(1);
    //     prefResponse.setProjectPreferenceId(1L);

    //     ArrayList<ProjectPreferenceResponseDto> prefResponseList = new ArrayList<ProjectPreferenceResponseDto>();
    //     prefResponseList.add(prefResponse);

    //     when(projectPreferenceService.setPreferences(prefList)).thenReturn(prefResponseList);
        
    //     mvc.perform(post("/preferences/save/project/preference").content())
    //             .andExpect(status().isOk());
    // } // new JSONArray(prefResponseList).toString() ?

    // TODO: can't get to make the router work in the test environment
    // @Test
    // public void getProjectPreferencesTest() throws Exception {
    //     ProjectPreferenceResponseDto prefResponse = new ProjectPreferenceResponseDto();
    //     prefResponse.setProjectId(1L);
    //     prefResponse.setUserId(1L);
    //     prefResponse.setRank(1);
    //     prefResponse.setProjectPreferenceId(1L);

    //     ArrayList<ProjectPreferenceResponseDto> prefResponseList = new ArrayList<ProjectPreferenceResponseDto>();
    //     prefResponseList.add(prefResponse);

    //     when(projectPreferenceService.getPreferences(any(Long.class))).thenReturn(prefResponseList);

    //     mvc.perform(get("/preferences/get/project/preference/1"))
    //             .andExpect(status().isAccepted())
    //             .andExpect(content().string(""));
    // }
}
