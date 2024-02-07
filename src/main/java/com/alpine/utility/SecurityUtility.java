package com.alpine.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtility {
    private static final String SALT = "salt"; // Salt should be protected carefully

    // Bean for creating a BCryptPasswordEncoder
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder with strength 12 and a custom SecureRandom salt
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    // Method to generate a random password
    @Bean
    public static String randomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();

        // Append random characters until the password reaches the desired length
        while (salt.length() < 18) {
            int index = (int) (rand.nextFloat()*SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        // Convert StringBuilder to String and return
        String saltStr = salt.toString();
        return saltStr;
    }
}
