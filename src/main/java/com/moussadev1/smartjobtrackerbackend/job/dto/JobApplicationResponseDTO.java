package com.moussadev1.smartjobtrackerbackend.job.dto;

import com.moussadev1.smartjobtrackerbackend.job.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record JobApplicationResponseDTO(
        UUID id,
        String title,
        String company,
        ApplicationStatus status,
        LocalDateTime createdAt
){}
