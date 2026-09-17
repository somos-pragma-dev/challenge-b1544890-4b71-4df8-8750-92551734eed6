package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para las solicitudes de creación y actualización de recursos.
 * Representa la estructura de datos que el cliente envía al API.
 */
public record ResourceRequest(
    @NotBlank(message = "El nombre del recurso es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String name,
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    String description,
    
    @NotNull(message = "El valor es obligatorio")
    @DecimalMin(value = "0.01", message = "El valor debe ser mayor a 0")
    BigDecimal value,
    
    @NotNull(message = "La fecha de adquisición es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate acquisitionDate,
    
    @NotNull(message = "El estado del recurso es obligatorio")
    ResourceStatus status,
    
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    String category,
    
    String location
) {
    /**
     * Enum que representa los estados posibles de un recurso.
     */
    public enum ResourceStatus {
        ACTIVO("Activo"),
        INACTIVO("Inactivo"),
        MANTENIMIENTO("En mantenimiento"),
        BAJA("Dado de baja");
        
        private final String displayName;
        
        ResourceStatus(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    /**
     * Constructor que normaliza los datos antes de asignarlos.
     */
    public ResourceRequest {
        if (name != null) {
            name = name.trim();
        }
        if (description != null) {
            description = description.trim();
        }
        if (category != null) {
            category = category.trim();
        }
        if (location != null) {
            location = location.trim();
        }
    }
    
    /**
     * Verifica si el recurso está activo para su uso.
     */
    public boolean isAvailable() {
        return status == ResourceStatus.ACTIVO;
    }
    
    /**
     * Valida que la fecha de adquisición no sea futura.
     */
    public boolean hasValidAcquisitionDate() {
        return acquisitionDate != null && !acquisitionDate.isAfter(LocalDate.now());
    }
    
    /**
     * Obtiene una representación en mayúsculas del nombre.
     */
    public String getNameUpperCase() {
        return name != null ? name.toUpperCase() : null;
    }
    
    /**
     * Método que verifica si el valor excede un umbral tertentu.
     */
    public boolean valueExceeds(BigDecimal threshold) {
        return value != null && value.compareTo(threshold) > 0;
    }
}