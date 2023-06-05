package com.example.demo.service.impl;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.config.UserDetailsImpl;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
public class UserDetailsServiceTest {
    @Autowired
    UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTest() {
        User test = new User();
        test.setEmail("test@exemple.com");
        test.setPassword("test");
        test.setCanvasUserId(1L);

        when(userRepository.findByEmail("test@exemple.com")).thenReturn(Optional.of(test));

        UserDetails user = userDetailsService.loadUserByUsername("test@exemple.com");
        assert(user.getUsername().equals("test@exemple.com"));
    }

    @Test
    public void loadUserByUsernameTestFail() {
        try{
            when(userRepository.findByEmail("test@exemple.com")).thenReturn(Optional.empty());
            userDetailsService.loadUserByUsername("test@exemple.com");
            assert(false);
        } catch (UsernameNotFoundException e) {
            assert(true);
        }
    }
}
