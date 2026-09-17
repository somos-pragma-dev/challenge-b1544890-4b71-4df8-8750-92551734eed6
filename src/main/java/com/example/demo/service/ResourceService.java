package com.example.demo.service;

import com.example.demo.dto.ResourceRequest;
import com.example.demo.dto.ResourceResponse;
import com.example.demo.model.Resource;
import com.example.demo.model.Resource.ResourceStatus;
import com.example.demo.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findAll() {
        return resourceRepository.findAll().stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ResourceResponse> findById(UUID id) {
        return resourceRepository.findById(id)
            .map(this::toResponse);
    }

    public ResourceResponse create(ResourceRequest request) {
        Resource resource = new Resource();
        resource.setId(UUID.randomUUID());
        resource.setName(request.name());
        resource.setDescription(request.description());
        resource.setValue(request.value());
        resource.setStatus(convertStatus(request.status()));
        resource.setAcquisitionDate(request.acquisitionDate());
        resource.setOwnerUsername("system");

        Resource saved = resourceRepository.save(resource);
        return toResponse(saved);
    }

    public Optional<ResourceResponse> update(UUID id, ResourceRequest request) {
        return resourceRepository.findById(id)
            .map(existing -> {
                existing.setName(request.name());
                existing.setDescription(request.description());
                existing.setValue(request.value());
                existing.setStatus(convertStatus(request.status()));
                existing.setAcquisitionDate(request.acquisitionDate());

                Resource updated = resourceRepository.save(existing);
                return toResponse(updated);
            });
    }

    public boolean delete(UUID id) {
        if (resourceRepository.existsById(id)) {
            resourceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> search(String name, Boolean available) {
        List<Resource> results;

        if (name != null && !name.isEmpty()) {
            results = resourceRepository.findByNameContainingIgnoreCase(name);
        } else if (available != null) {
            if (available) {
                results = resourceRepository.findByStatus(ResourceStatus.ACTIVE);
            } else {
                results = resourceRepository.findByStatus(ResourceStatus.INACTIVE);
            }
        } else {
            results = resourceRepository.findAll();
        }

        return results.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long count() {
        return resourceRepository.count();
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByValueGreaterThan(BigDecimal threshold) {
        return resourceRepository.findByValueGreaterThan(threshold).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ResourceResponse> findByStatus(ResourceStatus status) {
        return resourceRepository.findByStatus(status).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    private ResourceResponse toResponse(Resource resource) {
        return ResourceResponse.builder()
            .id(resource.getId())
            .name(resource.getName())
            .description(resource.getDescription())
            .value(resource.getValue())
            .status(resource.getStatus().name())
            .acquisitionDate(resource.getAcquisitionDate())
            .createdAt(resource.getCreatedAt())
            .updatedAt(resource.getUpdatedAt())
            .build();
    }

    private ResourceStatus convertStatus(ResourceRequest.ResourceStatus status) {
        if (status == null) {
            return ResourceStatus.INACTIVE;
        }
        return switch (status) {
            case ACTIVE -> ResourceStatus.ACTIVE;
            case INACTIVE -> ResourceStatus.INACTIVE;
            case MAINTENANCE -> ResourceStatus.MAINTENANCE;
            default -> ResourceStatus.INACTIVE;
        };
    }
}