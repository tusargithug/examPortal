package com.examportal.examPortal.Model;

import com.examportal.examPortal.Enum.OtpStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "otp")
public class Otp extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "otp")
    private String OTP;


    @Column(name = "expiry_date")
    private LocalDateTime expiryDateTime = LocalDateTime.now();

    @Enumerated
    @Column(name = "otp_type")
    private com.examportal.examPortal.Enum.OTPType OTPType;

    @Enumerated
    private OtpStatus otpStatus = OtpStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;


}
