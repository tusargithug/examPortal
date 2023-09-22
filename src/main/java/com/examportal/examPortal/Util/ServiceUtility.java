package com.examportal.examPortal.Util;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
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

    public static String generateNumericOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return otp.toString();
    }
    @Bean
    public static  String dateTimeToStringConversion(LocalDateTime localDateTime) {
        LocalDateTime currentLocalDateTime = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentLocalDateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }

}
