package com.agosh.userservice.service;

import com.agosh.userservice.dao.UserRepository;
import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.mapper.UserMapper;
import com.agosh.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
       User user = userRepository.save(UserMapper.toEntity(userDto));

        return UserMapper.toDto(user);
    }

    @Override
    public UserDto fetchUser(Integer userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(
                            () -> new RuntimeException("User with User Id "+ userId + " Not Found!!")
                    );
        return UserMapper.toDto(user);
    }
}
