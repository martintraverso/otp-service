package com.example.otp.service;

import com.example.otp.dto.CreateOtpRequest;
import com.example.otp.dto.CreateOtpResponse;
import com.example.otp.dto.ValidateOtpRequest;
import com.example.otp.model.OtpData;
import com.example.otp.model.OtpToken;
import com.example.otp.repository.OtpTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpTokenRepository otpTokenRepository;
    private final OtpGeneratorService otpGeneratorService;

    @Transactional
    public CreateOtpResponse createOtp(CreateOtpRequest request) {
        int length = (request.getLength() != null && request.getLength() != 6) ? request.getLength() : 6;

        int minutes = 0;
        int seconds = 0;

        if (request.getMinutes() != null) {
            minutes = request.getMinutes();
        } else if (request.getSeconds() != null) {
            seconds = request.getSeconds();
        } else {
            // Default to 5 minutes if neither are provided
            minutes = 5;
        }

        LocalDateTime expiration = LocalDateTime.now()
                .plusMinutes(minutes)
                .plusSeconds(seconds);

        String token = otpGeneratorService.generateToken(length);
        UUID guid = UUID.randomUUID();

        OtpData otpData = new OtpData(token, expiration);
        OtpToken otpToken = new OtpToken(guid, otpData);

        otpTokenRepository.save(otpToken);

        return CreateOtpResponse.builder()
                .guid(guid)
                .token(token)
                .expiration(expiration)
                .build();
    }

    @Transactional(readOnly = true)
    public void validateOtp(ValidateOtpRequest request) {
        OtpToken otpToken = otpTokenRepository.findById(request.getGuid())
                .orElseThrow(() -> new RuntimeException("Invalid Token"));

        OtpData data = otpToken.getData();

        if (!data.getToken().equals(request.getToken())) {
            throw new RuntimeException("Invalid Token");
        }

        if (LocalDateTime.now().isAfter(data.getExpiration())) {
            throw new RuntimeException("Invalid Token");
        }
    }
}
