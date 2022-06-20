package com.agosh.userservice.controller;

import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user/v1/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    endpoint for creating an user
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/create"
    )
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/get/{userId}"
    )
    public ResponseEntity<UserDto> fetchUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.fetchUser(userId), HttpStatus.OK);

    }
}
