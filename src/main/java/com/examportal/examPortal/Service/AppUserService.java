package com.examportal.examPortal.Service;

import com.examportal.examPortal.Dto.ChangePasswordDto;
import com.examportal.examPortal.Dto.DeleteDto;
import com.examportal.examPortal.Dto.LogInDto;
import com.examportal.examPortal.Dto.RegisterDto;
import com.examportal.examPortal.Enum.DeleteType;
import com.examportal.examPortal.Generic.GenericResponse;

public interface AppUserService {
    public GenericResponse registration(RegisterDto registerDto);

    public  GenericResponse logIn(LogInDto logInDto);

    public GenericResponse updateUser(RegisterDto registerDto);

    public GenericResponse changePassword(ChangePasswordDto changePasswordDto);

    public GenericResponse deleteById(DeleteDto deleteDto);
}
