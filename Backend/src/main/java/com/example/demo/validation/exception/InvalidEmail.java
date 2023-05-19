package com.example.demo.validation.exception;

public class InvalidEmail extends IllegalArgumentException {

    private String email;

    /**
     *  Instantiates a new invalid email exception.
     *  
     *  @param email the email received as an argument
     *  @param message more informations about the exception
     */
    public InvalidEmail(String email, String message) {
        super(message);
        this.email = email;
    }
    
    /**
     *  Gets the faulty email string.
     *  
     *  @return the email string provided
     */
    public String getEmail() {
        return email;
    }
}
