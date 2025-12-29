package com.moussadev1.smartjobtrackerbackend.job.dto;

import com.moussadev1.smartjobtrackerbackend.job.ApplicationStatus;

public record UpdateJobApplicationRequestDTO(
        String title,
        String company,
        ApplicationStatus status
) {}
