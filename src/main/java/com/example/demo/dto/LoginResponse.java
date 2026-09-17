package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.List;

/**
 * DTO que representa la respuesta exitosa de autenticación.
 * Contiene el token JWT y la información del usuario autenticado.
 */
public record LoginResponse(
    @JsonProperty("access_token")
    String accessToken,
    
    @JsonProperty("token_type")
    String tokenType,
    
    @JsonProperty("expires_in")
    long expiresIn,
    
    @JsonProperty("username")
    String username,
    
    @JsonProperty("roles")
    List<String> roles,
    
    @JsonProperty("issued_at")
    Instant issuedAt,
    
    @JsonProperty("expires_at")
    Instant expiresAt
) {
    /**
     * Constructor factory que crea la respuesta con timestamps automáticos.
     */
    public static LoginResponse of(String token, String username, List<String> roles, long expiresInSeconds) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(expiresInSeconds);
        
        return new LoginResponse(
            token,
            "Bearer",
            expiresInSeconds,
            username,
            roles,
            now,
            expiry
        );
    }
    
    /**
     * Verifica si el token está próximo a expirar (menos del 10% de tiempo restante).
     */
    public boolean isExpiringSoon() {
        if (expiresAt == null) return true;
        long remainingSeconds = expiresAt.getEpochSecond() - Instant.now().getEpochSecond();
        return remainingSeconds < (expiresIn * 0.1);
    }
    
    /**
     * Obtiene el tiempo restante hasta la expiración en segundos.
     */
    public long getRemainingSeconds() {
        if (expiresAt == null) return 0;
        long remaining = expiresAt.getEpochSecond() - Instant.now().getEpochSecond();
        return Math.max(0, remaining);
    }
    
    /**
     * Método para formato de respuesta simplificado.
     */
    public String toTokenOnly() {
        return accessToken;
    }
}