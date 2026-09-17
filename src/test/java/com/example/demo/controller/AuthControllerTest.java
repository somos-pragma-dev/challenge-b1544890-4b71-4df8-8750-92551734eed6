package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void shouldLoginSuccessfully() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser", "password123");
        LoginResponse loginResponse = LoginResponse.of("jwt.token.here", "testuser", 
            List.of("ROLE_USER"), 3600L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").value("jwt.token.here"))
            .andExpect(jsonPath("$.username").value("testuser"))
            .andExpect(jsonPath("$.roles[0]").value("ROLE_USER"))
            .andExpect(jsonPath("$.expiresInSeconds").value(3600));
    }

    @Test
    void shouldReturn401ForInvalidCredentials() throws Exception {
        LoginRequest loginRequest = new LoginRequest("wronguser", "wrongpassword");

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(null);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn400ForMissingUsername() throws Exception {
        String invalidRequest = "{\"password\":\"password123\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForMissingPassword() throws Exception {
        String invalidRequest = "{\"username\":\"testuser\"}";

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForEmptyRequestBody() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnTokenWithRoles() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin", "adminpass");
        LoginResponse loginResponse = LoginResponse.of("admin.jwt.token", "admin", 
            List.of("ROLE_ADMIN", "ROLE_USER"), 7200L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.roles").isArray())
            .andExpect(jsonPath("$.roles.length()").value(2));
    }

    @Test
    void shouldAcceptJsonContentType() throws Exception {
        LoginRequest loginRequest = new LoginRequest("testuser", "password");
        LoginResponse loginResponse = LoginResponse.of("token", "testuser", List.of(), 3600L);

        when(authService.authenticate(any(LoginRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk());
    }
}