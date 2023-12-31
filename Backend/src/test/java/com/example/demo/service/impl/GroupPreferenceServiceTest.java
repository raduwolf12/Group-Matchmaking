package com.example.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.repository.GroupPreferenceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupPreferenceService;
import com.example.demo.validation.exception.UserNotFoundException;

@SpringBootTest
public class GroupPreferenceServiceTest {
    @Autowired
    private GroupPreferenceService groupPreferenceService;

    @MockBean
    private GroupPreferenceRepository groupPreferenceRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createGroupPreferenceTest() throws UserNotFoundException {
        // TODO : implement this test (seems pretty hard to do)
    }

    @Test
    public void getGroupPreferenceTest() {
        // TODO : implement this test (seems pretty hard to do)
    }
}