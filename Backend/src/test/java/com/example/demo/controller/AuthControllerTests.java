package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.entity.enums.Role;
import com.example.demo.model.pojo.AuthRequest;
import com.example.demo.utils.JwtUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;
    
    @Test
    @WithAnonymousUser
    public void authenticateUserTest() throws Exception {
        String mail = "test@exemple.com";
        String password = "test";
        
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(Role.STUDENT);
        Authentication authentication = new UsernamePasswordAuthenticationToken(mail, password, authorities);
        
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password))).thenReturn(authentication);

        when(jwtUtils.generateJwtToken(any(Authentication.class))).thenReturn("token");

        mvc.perform(post("/auth/login")
                .contentType("application/json")
                .content("{\"email\":\"" + mail + "\", \"password\":\"" + password + "\"}"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithAnonymousUser
    public void authenticateNonExistingUserTest() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException("Non-existing user"));
        
        mvc.perform(post("/auth/login")
                .contentType("application/json")
                .content("{\"email\":\"test@exemple.com\", \"password\":\"test\"}"))
                .andExpect(status().isUnauthorized());
    }
}
