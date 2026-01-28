package com.example.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOtpRequest {
    private Integer length;
    private Integer minutes;
    private Integer seconds;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOtpResponse {
    private UUID guid;
    private String token;
    private LocalDateTime expiration;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOtpRequest {
    private UUID guid;
    private String token;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOtpResponse {
    private String message;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String error;
}
