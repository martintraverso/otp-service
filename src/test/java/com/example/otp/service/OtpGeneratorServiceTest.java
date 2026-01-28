package com.example.otp.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OtpGeneratorServiceTest {

    private final OtpGeneratorService otpGeneratorService = new OtpGeneratorService();

    @Test
    public void testGenerateTokenLength() {
        int length = 6;
        String token = otpGeneratorService.generateToken(length);
        assertNotNull(token);
        assertEquals(length, token.length());
    }

    @Test
    public void testGenerateTokenCustomLength() {
        int length = 8;
        String token = otpGeneratorService.generateToken(length);
        assertNotNull(token);
        assertEquals(length, token.length());
    }

    @Test
    public void testGenerateTokenContent() {
        String token = otpGeneratorService.generateToken(100);
        assertTrue(token.matches("[1-9]+"), "Token should only contain digits 1-9");
    }
}
