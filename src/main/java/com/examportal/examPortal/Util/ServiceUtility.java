package com.examportal.examPortal.Util;

import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ServiceUtility {

    @Bean
    public static String yourOtp() {
        int otpLength = 4;
        String otp = generateNumericOTP(otpLength);
        return otp;
    }

    //Otp generator
    public static String generateNumericOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return otp.toString();
    }

    //OtpChar generator
    @Bean
    public static String generateAlphaCharacter(int n) {
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder otpChar = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (randomString.length() * Math.random());

            otpChar.append(randomString.charAt(index));
        }
        return otpChar.toString();
    }

    @Bean
    public static String dateTimeToStringConversion(LocalDateTime localDateTime) {
        LocalDateTime currentLocalDateTime = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }

}
