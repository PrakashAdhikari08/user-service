package com.agosh.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<List<String>> dtoValidationExceptionForMethods(MethodArgumentNotValidException ex){
//
//        log.info("Invalid arguments found : " + ex.getMessage());
//        // Get the error messages for invalid fields
//        List<String> errors = new ArrayList<>();
//
//    ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .forEach( e -> errors.add(String.valueOf(e)));
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//
//    }
}
