package com.example.demo.controller;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.model.Resource;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.service.ResourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ResourceService resourceService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private String validToken;
    private String expiredToken;
    private String invalidToken;

    @BeforeEach
    void setUp() {
        validToken = "Bearer valid.jwt.token";
        expiredToken = "Bearer expired.jwt.token";
        invalidToken = "Bearer invalid.jwt.token";
    }

    @Test
    void shouldAccessProtectedEndpointWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(java.util.UUID.randomUUID());
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(resource);
        
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/resources")
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("TestResource"));
    }

    @Test
    void shouldReturn401WithExpiredToken() throws Exception {
        when(jwtTokenUtil.extractUsername("expired.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("expired.jwt.token", "testuser")).thenReturn(false);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", expiredToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithInvalidToken() throws Exception {
        when(jwtTokenUtil.extractUsername("invalid.jwt.token")).thenReturn(null);

        mockMvc.perform(get("/api/resources")
                .header("Authorization", invalidToken))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401WithoutToken() throws Exception {
        mockMvc.perform(get("/api/resources"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldCreateResourceWithValidToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);
        
        Resource savedResource = new Resource();
        savedResource.setId(java.util.UUID.randomUUID());
        savedResource.setName("NewResource");
        savedResource.setValue(BigDecimal.valueOf(500));
        savedResource.setAcquisitionDate(LocalDate.now());
        savedResource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(savedResource);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.create(any(ResourceRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/resources")
                .header("Authorization", validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("NewResource"));
    }

    @Test
    void shouldGetResourceByIdWithValidToken() throws Exception {
        Resource resource = new Resource();
        resource.setId(java.util.UUID.randomUUID());
        resource.setName("TestResource");
        resource.setValue(BigDecimal.valueOf(100));
        resource.setAcquisitionDate(LocalDate.now());
        resource.setStatus(ResourceRequest.ResourceStatus.ACTIVE);

        ResourceResponse response = ResourceResponse.fromEntity(resource);

        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(resource.getId())).thenReturn(Optional.of(response));

        mockMvc.perform(get("/api/resources/" + resource.getId())
                .header("Authorization", validToken))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("TestResource"));
    }

    @Test
    void shouldReturn404ForNonExistentResource() throws Exception {
        java.util.UUID nonExistentId = java.util.UUID.randomUUID();
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.findById(nonExistentId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/resources/" + nonExistentId)
                .header("Authorization", validToken))
            .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteResourceWithValidToken() throws Exception {
        java.util.UUID resourceId = java.util.UUID.randomUUID();
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn("testuser");
        when(jwtTokenUtil.validateToken("valid.jwt.token", "testuser")).thenReturn(true);
        when(resourceService.delete(resourceId)).thenReturn(true);

        mockMvc.perform(delete("/api/resources/" + resourceId)
                .header("Authorization", validToken)
                .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    void shouldRejectPostWithoutToken() throws Exception {
        ResourceRequest request = new ResourceRequest("NewResource", 
            BigDecimal.valueOf(500), LocalDate.now(), ResourceRequest.ResourceStatus.ACTIVE);

        mockMvc.perform(post("/api/resources")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectDeleteWithoutToken() throws Exception {
        mockMvc.perform(delete("/api/resources/1")
                .with(csrf()))
            .andExpect(status().isUnauthorized());
    }
}