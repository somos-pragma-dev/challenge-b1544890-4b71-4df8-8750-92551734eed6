package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO que representa la solicitud de autenticación del usuario.
 * Contiene las credenciales necesarias para validar la identidad.
 */
public record LoginRequest(
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El usuario debe tener entre 3 y 50 caracteres")
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    String password
) {
    /**
     * Constructor compact que permite validación adicional si es necesario.
     */
    public LoginRequest {
        if (username != null) {
            username = username.trim();
        }
        if (password != null) {
            password = password.trim();
        }
    }
    
    /**
     * Método para obtener el nombre de usuario en mayúsculas para comparación.
     */
    public String usernameUpperCase() {
        return username.toUpperCase();
    }
    
    /**
     * Verifica si las credenciales están vacías después de trim.
     */
    public boolean hasValidCredentials() {
        return username != null && !username.isBlank() && 
               password != null && !password.isBlank();
    }
}