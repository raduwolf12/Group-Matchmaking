package com.example.demo.service.impl;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.service.EmailingService;

@SpringBootTest
public class EmailingServiceTest {
    @Autowired
    private EmailingService emailingService;

    @MockBean
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.prettyName}")
    private String prettyName;

    @Test
    public void sendEmailTest() {
        emailingService.sendEmail("test@exemple.com", "Test", "Some body");
        verify(mailSender).send(argThat(new ArgumentMatcher<SimpleMailMessage>() {
            @Override
            public boolean matches(SimpleMailMessage argument) {
                return  argument.getTo()[0].equals("test@exemple.com") &&
                        argument.getSubject().equals("Test") &&
                        argument.getText().equals("Some body") &&
                        (argument.getFrom().equals(from) || argument.getFrom().equals("\"" + prettyName + "\" <" + from +">"));
            }
        }));
    }
}
