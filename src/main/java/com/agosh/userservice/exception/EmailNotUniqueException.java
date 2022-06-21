package com.agosh.userservice.exception;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailNotUniqueException extends RuntimeException{

    private String message;
    public EmailNotUniqueException(String message) {
        super(message);
        this.message = message;
    }
}
