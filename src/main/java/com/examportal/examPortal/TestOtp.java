package com.examportal.examPortal;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;



public class TestOtp {
  //  public static void main(String[] args) {
//        int otpLength = 4; // Length of the OTP
//        String otp = generateNumericOTP(otpLength);
//        System.out.println(" OTP: " + otp);
//    }
//
//    public static String generateNumericOTP(int length) {
//        Random random = new Random();
//        StringBuilder otp = new StringBuilder();
//
//        for (int i = 0; i < length; i++) {
//            otp.append(random.nextInt(10)); // Generate a random digit (0-9)
//        }
//
//        return otp.toString();
//    }
//    public static String randomAlphaNumericKey(int count) {
//
//        byte[] array = new byte[256];
//        new SecureRandom().nextBytes(array);
//
//        String randomString
//                = new String(array, StandardCharsets.UTF_8);
//
//        // Create a StringBuffer to store the result
//        StringBuffer r = new StringBuffer();
//
//        // Append first 20 alphanumeric characters
//        // from the generated random String into the result
//        for (int k = 0; k < randomString.length(); k++) {
//            char ch = randomString.charAt(k);
//
//            if ((ch >= 'A' && ch <= 'Z') && count > 0) {
//                r.append(ch);
//                count--;
//            }
//        }
//        System.out.println(r);
//        // return the resultant string
//        return r.toString();
//    }
//        public String getSaltString () {
//            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//            StringBuilder salt = new StringBuilder();
//            Random rnd = new Random();
//            while (salt.length() < 18) { // length of the random string.
//                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//                salt.append(SALTCHARS.charAt(index));
//            }
//            String saltStr = salt.toString();
//            return saltStr;
//        }
    }


