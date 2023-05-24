package com.examportal.examPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogInDto {
    private String email;
    private String userName;
    private String password;
}
