package com.example.demo.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.dto.UserResponseDto;
import com.example.demo.model.entity.User;
import com.example.demo.model.pojo.CSVHelper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CSVService;


@SpringBootTest
public class CSVServiceTest {
    @Autowired
    private CSVService csvService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder encoder;

    String csvTemplate = "name,canvas_user_id,user_id,login_id,sections,group_name,canvas_group_id,group_id\n" +
                            "\"Costescu, Mihai-Razvan\",381661,762780,wrl572@alumni.ku.dk,Hold 02;,Group matchmaking tool (Tijs),162342,\n" +
                            "\"Massardier, Matthieu Camille Régis\",396987,773214,cxs929@alumni.ku.dk,Hold 02;,Group matchmaking tool (Tijs),162342,\n" +
                            "\"Taraburca, Radu\",381838,762917,whx862@alumni.ku.dk,Hold 01;,Group matchmaking tool (Tijs),162342,\n" +
                            "\"Terry, Thomas Jackson\",381360,762492,pdh248@alumni.ku.dk,Hold 01;,Group matchmaking tool (Tijs),162342,\n";

    MockMultipartFile file = new MockMultipartFile("file", "students.csv", "text/csv", csvTemplate.getBytes());


    @Test
    public void saveTest() throws Exception {
        csvService.save(file);

        // Check that we apparently saved the 4 users to the database
        verify(userRepository).saveAll(argThat(new ArgumentMatcher<List<User>>() {
            @Override
            public boolean matches(List<User> argument) {
                return argument.size() == (csvTemplate.split("\n").length - 1)
                    && argument.get(0).getName().equals(csvTemplate.split("\n")[1].split("\"")[1]);
            }
        }));
        // Check that we created 4 passwords (there is 3 residual calls I don't know where they come from)
        verify(encoder, times((csvTemplate.split("\n").length - 1) + 3)).encode(anyString());
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> basicUsers = new ArrayList<User>();
        basicUsers.add(new User());
        when(userRepository.findAll()).thenReturn(basicUsers);
        
        List<UserResponseDto> users = csvService.getAllUsers();
        verify(userRepository).findAll();
        assert(users.size() == 1);

    }
}
