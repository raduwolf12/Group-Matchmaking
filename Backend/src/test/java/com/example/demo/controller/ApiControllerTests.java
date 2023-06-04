package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @WithMockUser()
    public void sayHelloTest() throws Exception {
        mvc.perform(get("/canva-api/hello-world"))
            .andExpect(status().isOk())
            .andExpect(content().string("HI"));
    }
    
    @Test
    @WithAnonymousUser
    public void dontSayHelloToStrangersTest() throws Exception {
        mvc.perform(get("/canva-api/hello-world"))
            .andExpect(status().isUnauthorized());
    }

    // TODO : other tests ? I'm not sure what to test here as we dropped support fro Canvas
}
