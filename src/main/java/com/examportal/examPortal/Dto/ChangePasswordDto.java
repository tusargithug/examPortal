package com.examportal.examPortal.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordDto {
    private String id;
    private String userName;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
