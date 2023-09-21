package com.examportal.examPortal;

import java.util.Random;

public class TestOtp {
    public static void main(String[] args) {
        int otpLength = 4; // Length of the OTP
        String otp = generateNumericOTP(otpLength);
        System.out.println(" OTP: " + otp);
    }

    public static String generateNumericOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Generate a random digit (0-9)
        }

        return otp.toString();
    }
}
