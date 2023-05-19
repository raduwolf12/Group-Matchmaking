package com.example.demo.service.impl;

import com.example.demo.service.EmailingService;
import com.example.demo.validation.exception.InvalidEmail;

public class EmailingServiceImpl implements EmailingService {

    @Override
    public void sendEmail(String to, String subject, String body) throws InvalidEmail, RuntimeException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendEmail'");
    }
    
}
