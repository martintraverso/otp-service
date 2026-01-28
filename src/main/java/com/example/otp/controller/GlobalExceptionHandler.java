package com.example.otp.controller;

import com.example.otp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // Requirements say "All validation errors return HTTP 500" with body { "error":
        // "Invalid Token" }
        // For general errors or validation failures, we return 500 and the message.
        // However, the specific requirement "Invalid Token" seems to be for validation
        // failure.
        // I'll return "Invalid Token" if the exception message matches, otherwise just
        // the exception message or generic.

        String message = ex.getMessage();
        if (message == null || message.isEmpty()) {
            message = "Internal Server Error";
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(message));
    }
}
