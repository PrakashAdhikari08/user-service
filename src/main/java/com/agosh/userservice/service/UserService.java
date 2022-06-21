package com.agosh.userservice.service;

import com.agosh.userservice.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;

public interface UserService {

    UserDto createUser(@Valid UserDto userDto);

    UserDto fetchUser(@Valid @Min(value = 1, message = "User Id should be greater than 0!!")
                              Integer userId);

    String deleteUser(@Valid @Min(value = 1, message = "User Id should be greater than 0!!")
                              Integer userId);

    UserDto updateUser(@Valid UserDto userDto,
                       @Valid @Min(value = 1, message = "User Id should be greater than 0!!")
                               Integer userId);
}
