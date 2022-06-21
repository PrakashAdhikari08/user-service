package com.agosh.userservice;

import com.agosh.userservice.dto.UserDto;
import com.agosh.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void createUserControllerTest(){

        UserDto userDto = generateUserDto();

        ResponseEntity<UserDto> response =
                restTemplate.postForEntity(URI.create("/user/v1/create"),
                        userDto, UserDto.class);

        assertEquals(201, response.getStatusCode().value());

    }

    @Test
    public void getUserByIdControllerTest() {
        UserDto userDto = generateUserDto();

        userService.createUser(userDto);

        ResponseEntity<UserDto> response =
                restTemplate.getForEntity("/user/v1/get/1",
                        UserDto.class);
        UserDto responseUser = response.getBody();
        assertEquals(responseUser.getUserId(), 1);
        assertEquals(200, response.getStatusCode().value() );

    }

    @Test
    public void deleteUserByIdControllerTest() {
        UserDto userDto = generateUserDto();

        userService.createUser(userDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Integer> map= new LinkedMultiValueMap<>();
        map.add("userId", 1);;
        HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange("/user/v1/delete",HttpMethod.DELETE, request, String.class);

        assertEquals(response.getStatusCode().value(), 200);


    }

    @Test
    public void updateUserControllerTest(){

        UserDto userDto = generateUserDto();

        userService.createUser(userDto);

        userDto.setFirstName("ABC");
        userDto.setLastName("DEF");

        HttpEntity<UserDto> request = new HttpEntity<>(userDto);

        restTemplate.put("/user/v1/update/1", userDto);

        ResponseEntity<UserDto> response =
                restTemplate.getForEntity("/user/v1/get/1",
                        UserDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(response.getBody().getFirstName(), "ABC");
        assertEquals(response.getBody().getLastName(), "DEF");



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
