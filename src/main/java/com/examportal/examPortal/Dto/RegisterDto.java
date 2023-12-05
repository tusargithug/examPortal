package com.examportal.examPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    private String id;

    private String firstName;

    private String lastName;

    private String rollNo;

    private String mobileNo;

    private String email;

    private String password;

    private String confirmPassword;

    private String userName;

    private String roleType;

    private String token;

}
