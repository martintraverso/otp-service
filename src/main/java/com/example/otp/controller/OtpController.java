package com.example.otp.controller;

import com.example.otp.dto.CreateOtpRequest;
import com.example.otp.dto.CreateOtpResponse;
import com.example.otp.dto.ValidateOtpRequest;
import com.example.otp.dto.ValidateOtpResponse;
import com.example.otp.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/create")
    public ResponseEntity<CreateOtpResponse> createOtp(@RequestBody CreateOtpRequest request) {
        CreateOtpResponse response = otpService.createOtp(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateOtpResponse> validateOtp(@RequestBody ValidateOtpRequest request) {
        otpService.validateOtp(request);
        return ResponseEntity.ok(new ValidateOtpResponse("Success"));
    }
}
