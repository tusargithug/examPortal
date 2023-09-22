package com.examportal.examPortal.Repository;

import com.examportal.examPortal.Enum.OTPType;
import com.examportal.examPortal.Model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OtpRepo extends JpaRepository<Otp,String> {
    Optional<Otp> findByOTP(String otp);
    Optional<Otp> findByOTPAndExpiryDateTimeAndOTPType(String otp, LocalDateTime now, OTPType signIn);
}
