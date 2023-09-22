package com.examportal.examPortal.Dto;

import com.examportal.examPortal.Enum.OTPType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OtpVerificationDto {
    private String otp;
    private OTPType otpType ;
}
