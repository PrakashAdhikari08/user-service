package com.agosh.userservice.controller;

import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    @Operation(description = "Create a new user.")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{userId}")
    @Operation(description = "get user details by user id")
    public ResponseEntity<UserDto> fetchUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.fetchUser(userId), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @Operation(description = "Delete User from Database using user id")
    public ResponseEntity<String> deleteUser(@RequestParam Integer userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{userId}")
    @Operation(description = "Update details of a user by Id")
    public ResponseEntity<UserDto> updateUserDetails(@RequestBody UserDto userDto,
                                                     @PathVariable Integer userId) {
        return new ResponseEntity<>(userService.updateUser(userDto, userId), HttpStatus.OK);
    }
}
