package com.examportal.examPortal.Dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    private String firstName;

    private String lastName;

    private String rollNo;

    private String mobileNo;

    private String email;

    private String password;

    private String confirmPassword;

    private String userName;

}
