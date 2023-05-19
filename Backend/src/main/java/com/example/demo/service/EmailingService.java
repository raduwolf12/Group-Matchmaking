package com.example.demo.service;

import com.example.demo.validation.exception.InvalidEmail;

/**
 * The Interface EmailingService.
 */
public interface EmailingService {
   
    /**
     * Send email.
     *
     * @param to send to - must be a valid email address
     * @param subject the subject
     * @param body the text
     * @throws InvalidEmail if to is not a valid email address
     * @throws RuntimeException if there is an error sending the email
     */
    public void sendEmail(String to, String subject, String body) throws InvalidEmail, RuntimeException;

    
}
