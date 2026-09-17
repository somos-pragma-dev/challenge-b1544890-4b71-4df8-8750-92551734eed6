package com.example.demo.dto;

import com.example.demo.model.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResourceResponse(
    UUID id,
    String name,
    String description,
    ResourceRequest.ResourceStatus status,
    BigDecimal value,
    LocalDate acquisitionDate,
    String ownerUsername,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<String> tags
) {
    public static ResourceResponse fromEntity(Resource resource) {
        return new ResourceResponse(
            resource.getId(),
            resource.getName(),
            resource.getDescription(),
            resource.getStatus(),
            resource.getValue(),
            resource.getAcquisitionDate(),
            resource.getOwnerUsername(),
            resource.getCreatedAt(),
            resource.getUpdatedAt(),
            resource.getTags() != null ? resource.getTags() : List.of()
        );
    }

    public boolean isAvailable() {
        return status == ResourceRequest.ResourceStatus.ACTIVE || 
               status == ResourceRequest.ResourceStatus.AVAILABLE;
    }

    public boolean isExpired() {
        return acquisitionDate != null && 
               acquisitionDate.plusYears(5).isBefore(LocalDate.now());
    }

    public long getAgeInDays() {
        if (acquisitionDate == null) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(acquisitionDate, LocalDate.now());
    }

    public boolean hasValue() {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public String getFormattedValue() {
        if (value == null) return "N/A";
        return String.format("$%,.2f", value);
    }

    public boolean belongsTo(String username) {
        return ownerUsername != null && ownerUsername.equalsIgnoreCase(username);
    }

    public boolean hasTags() {
        return tags != null && !tags.isEmpty();
    }

    public String getTagsAsString() {
        if (tags == null || tags.isEmpty()) return "";
        return String.join(", ", tags);
    }
}