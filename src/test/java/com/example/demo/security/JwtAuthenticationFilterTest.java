package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private FilterChain filterChain;

    private JwtAuthenticationFilter filter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        filter = new JwtAuthenticationFilter(jwtTokenUtil);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAuthenticateValidToken() throws ServletException, IOException {
        String validToken = "Bearer valid.jwt.token";
        String username = "testuser";
        List<String> roles = List.of("ROLE_USER");

        request.addHeader("Authorization", validToken);
        when(jwtTokenUtil.extractUsername("valid.jwt.token")).thenReturn(username);
        when(jwtTokenUtil.extractRoles("valid.jwt.token")).thenReturn(roles);
        when(jwtTokenUtil.validateToken("valid.jwt.token", username)).thenReturn(true);

        filter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken auth = 
            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        
        assertNotNull(auth);
        assertEquals(username, auth.getPrincipal());
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateInvalidToken() throws ServletException, IOException {
        request.addHeader("Authorization", "Bearer invalid.token");
        when(jwtTokenUtil.extractUsername("invalid.token")).thenReturn(null);

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateExpiredToken() throws ServletException, IOException {
        String expiredToken = "Bearer expired.token";
        String username = "testuser";

        request.addHeader("Authorization", expiredToken);
        when(jwtTokenUtil.extractUsername("expired.token")).thenReturn(username);
        when(jwtTokenUtil.validateToken("expired.token", username)).thenReturn(false);

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateWhenNoAuthorizationHeader() throws ServletException, IOException {
        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateMalformedAuthorizationHeader() throws ServletException, IOException {
        request.addHeader("Authorization", "NotBearer token");

        filter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldExtractMultipleRoles() throws ServletException, IOException {
        String token = "Bearer token.with.roles";
        String username = "admin";
        List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");

        request.addHeader("Authorization", token);
        when(jwtTokenUtil.extractUsername("token.with.roles")).thenReturn(username);
        when(jwtTokenUtil.extractRoles("token.with.roles")).thenReturn(roles);
        when(jwtTokenUtil.validateToken("token.with.roles", username)).thenReturn(true);

        filter.doFilterInternal(request, response, filterChain);

        UsernamePasswordAuthenticationToken auth = 
            (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        
        assertNotNull(auth);
        assertEquals(2, auth.getAuthorities().size());
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}