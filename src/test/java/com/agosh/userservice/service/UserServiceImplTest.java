package com.agosh.userservice.service;

import com.agosh.userservice.dao.UserRepository;
import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.exception.UserNotFoundException;
import com.agosh.userservice.mapper.UserMapper;
import com.agosh.userservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Autowired
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
         userService= new UserServiceImpl(userRepository);
    }


    @Test
    void createUser() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(generateUser());

        userService.createUser(generateUserDto());

        verify(userRepository).save(any());



    }

    @Test
    void fetchUserNotFoundById() {
        assertThrows(UserNotFoundException.class, () -> userService.fetchUser(anyInt()));
    }

    @Test
    void fetchUser() {
        User user = generateUser();
        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        userService.fetchUser(1);
        verify(userRepository).findById(anyInt());
    }

    @Test
    void deleteUser() {
        when(userRepository.existsById(anyInt())).thenReturn(true);
        userService.deleteUser(1);
        verify(userRepository).deleteById(anyInt());
    }

    @Test
    void updateUser() {
        UserDto user = generateUserDto();
        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(generateUser()));
        user.setLastName("A");
        UserDto userDto = userService.updateUser(user, 1);
        assertEquals(userDto.getLastName(), "A");

    }


    private User generateUser(){
        return User
                .builder()
                .userId(1)
                .firstName("Prakash")
                .lastName("Adhikari")
                .dateOfBirth(LocalDate.of(2020, 11, 11))
                .email("prakashadhi08@gmail.com")
                .build();
    }

    private UserDto generateUserDto(){
        return UserDto
                .builder()
                .userId(1)
                .firstName("Prakash")
                .lastName("Adhikari")
                .dateOfBirth(LocalDate.of(2020, 11, 11))
                .email("prakashadhi08@gmail.com")
                .build();
    }
}