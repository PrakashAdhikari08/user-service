package com.agosh.userservice.service;

import com.agosh.userservice.dao.UserRepository;
import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.exception.EmailNotUniqueException;
import com.agosh.userservice.exception.UserNotFoundException;
import com.agosh.userservice.mapper.UserMapper;
import com.agosh.userservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


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
        if(userRepository.existsByEmail(userDto.getEmail()))
               throw new EmailNotUniqueException("Email " + userDto.getEmail() + " already is use.");
       User user = userRepository.save(UserMapper.toEntity(userDto));

        return UserMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto fetchUser(Integer userId) {
        User user = userRepository.findById(userId)
                    .orElseThrow(
                            () -> new UserNotFoundException("User with User Id "+ userId + " Not Found!!")
                    );
        return UserMapper.toDto(user);
    }

//the best way is to deactivate the user instead of deleting the user in real world.
    @Override
    public String deleteUser(Integer userId) {
        if(!userRepository.existsById(userId))
            throw new UserNotFoundException("User with Id " + userId + " not present!!");
        userRepository.deleteById(userId);
        return "User deleted with ID: " + userId;

    }

/*
The email being the unique property we don't allow for update of this email it should be done with validation process
as this update is not done using the update api in case of if it is the login details
 */
    @Override
    @Transactional(readOnly = false)
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with id : " + userId)
        );
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        return UserMapper.toDto(user);
    }
}
