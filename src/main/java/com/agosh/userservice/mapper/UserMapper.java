package com.agosh.userservice.mapper;

import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.model.User;

public class UserMapper {

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .dateOfBirth(userDto.getDateOfBirth())
//                .userId(userDto.getUserId())
                .build();
    }

    public static UserDto toDto (User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
