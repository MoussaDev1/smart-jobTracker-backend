package com.moussadev1.smartjobtrackerbackend.job.dto;

import com.moussadev1.smartjobtrackerbackend.job.ApplicationStatus;

public record UpdateStatusRequestDTO(ApplicationStatus status) {
}
