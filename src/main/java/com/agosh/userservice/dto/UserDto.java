package com.agosh.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer userId;
    @NotBlank(message = "First Name cannot be blank!!")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank!!")
    private String lastName;
    @Past(message = "Date of Birth must be in past!!")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Email(regexp = ".+[@].+[\\.].+", message = "Please Enter Valid Email!!")
    @NotBlank(message = "Email Cannot be blank")
    private String email;
}
