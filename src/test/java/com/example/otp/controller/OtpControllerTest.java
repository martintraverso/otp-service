package com.example.otp.controller;

import com.example.otp.dto.CreateOtpRequest;
import com.example.otp.dto.CreateOtpResponse;
import com.example.otp.dto.ValidateOtpRequest;
import com.example.otp.service.OtpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OtpController.class)
public class OtpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OtpService otpService;

    @Test
    public void testCreateOtp() throws Exception {
        CreateOtpRequest request = new CreateOtpRequest();
        request.setLength(6);

        CreateOtpResponse response = CreateOtpResponse.builder()
                .guid(UUID.randomUUID())
                .token("123456")
                .expiration(LocalDateTime.now().plusMinutes(5))
                .build();

        Mockito.when(otpService.createOtp(any(CreateOtpRequest.class))).thenReturn(response);

        mockMvc.perform(post("/otp/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("123456"))
                .andExpect(jsonPath("$.guid").exists());
    }

    @Test
    public void testValidateOtpSuccess() throws Exception {
        ValidateOtpRequest request = new ValidateOtpRequest(UUID.randomUUID(), "123456");

        Mockito.doNothing().when(otpService).validateOtp(any(ValidateOtpRequest.class));

        mockMvc.perform(post("/otp/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    public void testValidateOtpFailure() throws Exception {
        ValidateOtpRequest request = new ValidateOtpRequest(UUID.randomUUID(), "123456");

        Mockito.doThrow(new RuntimeException("Invalid Token"))
                .when(otpService).validateOtp(any(ValidateOtpRequest.class));

        mockMvc.perform(post("/otp/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Invalid Token"));
    }
}
