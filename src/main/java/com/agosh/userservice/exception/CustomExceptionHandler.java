package com.agosh.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> dtoValidationException(ConstraintViolationException exception){

        List<String> errors = new ArrayList<>();
        exception
                .getConstraintViolations()
                .stream()
                .forEach(error -> errors.add(error.getMessage()));
        exception.getMessage();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
