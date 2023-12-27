package com.examportal.examPortal.Service;

import com.examportal.examPortal.Dto.*;
import com.examportal.examPortal.Generic.GenericResponse;

public interface AppUserService {
    GenericResponse registration(RegisterDto registerDto);

    GenericResponse logIn(LogInDto logInDto);

    GenericResponse generateOtp(GenerateOtpDto generateOtpDto);

    GenericResponse otpVerification(OtpVerificationDto otpVerificationDto);

    GenericResponse updateUser(RegisterDto registerDto);

    GenericResponse changePassword(ChangePasswordDto changePasswordDto);

    GenericResponse deleteById(DeleteDto deleteDto);

    GenericResponse getAll();

    GenericResponse getById(String id);
}
