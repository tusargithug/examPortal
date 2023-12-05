package com.examportal.examPortal.Service;

import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Generic.GenericResponse;

public interface AppUserService {
    public GenericResponse registration(RegisterDto registerDto);

    public GenericResponse logIn(LogInDto logInDto);
    GenericResponse generateOtp(GenerateOtpDto generateOtpDto);

    GenericResponse otpVerification(OtpVerificationDto otpVerificationDto);

    public GenericResponse updateUser(RegisterDto registerDto);

    public GenericResponse changePassword(ChangePasswordDto changePasswordDto);

    public GenericResponse deleteById(DeleteDto deleteDto);

    public GenericResponse getAll();

    GenericResponse getById(String id);
}
