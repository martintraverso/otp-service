package com.example.otp.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpGeneratorService {

    private final Random random = new Random();

    public String generateToken(int length) {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Generates a number from 0 to 8 (inclusive), then adds 1 to make it 1 to 9
            int randomNumber = random.nextInt(9) + 1;
            token.append(randomNumber);
        }
        return token.toString();
    }
}
