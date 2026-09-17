package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final Map<String, UserCredentials> registeredUsers = new ConcurrentHashMap<>();

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenUtil jwtTokenUtil,
                       UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        registeredUsers.put("ADMIN", new UserCredentials("ADMIN", "admin123", List.of("ROLE_ADMIN", "ROLE_USER")));
        registeredUsers.put("USER", new UserCredentials("USER", "user123", List.of("ROLE_USER")));
    }

    public LoginResponse authenticate(LoginRequest request) {
        if (request == null || !request.hasValidCredentials()) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String username = request.usernameUpperCase();
        UserCredentials credentials = registeredUsers.get(username);

        if (credentials == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, credentials.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            long expiresIn = jwtTokenUtil.getExpirationTime();

            List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            return LoginResponse.of(token, username, roles, expiresIn);

        } catch (Exception e) {
            throw new BadCredentialsException("Autenticación fallida: " + e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        return jwtTokenUtil.validateToken(token);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.extractUsername(token);
    }

    public LoginResponse refreshToken(String token) {
        if (!validateToken(token)) {
            throw new BadCredentialsException("Token inválido o expirado");
        }

        String username = getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newToken = jwtTokenUtil.generateToken(userDetails);
        long expiresIn = jwtTokenUtil.getExpirationTime();

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        return LoginResponse.of(newToken, username, roles, expiresIn);
    }

    public void registerUser(String username, String password, List<String> roles) {
        if (registeredUsers.containsKey(username.toUpperCase())) {
            throw new IllegalArgumentException("El usuario ya existe: " + username);
        }
        registeredUsers.put(username.toUpperCase(), new UserCredentials(username, password, roles));
    }

    public boolean userExists(String username) {
        return registeredUsers.containsKey(username.toUpperCase());
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(registeredUsers.keySet());
    }

    private record UserCredentials(String username, String password, List<String> roles) {
        UserDetails toUserDetails() {
            return User.builder()
                .username(username)
                .password(password)
                .authorities(roles.toArray(new String[0]))
                .build();
        }
        
        String getPassword() {
            return password;
        }
    }
}