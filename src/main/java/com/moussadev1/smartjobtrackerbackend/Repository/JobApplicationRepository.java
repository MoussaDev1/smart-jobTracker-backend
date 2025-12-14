package com.moussadev1.smartjobtrackerbackend.Repository;

import com.moussadev1.smartjobtrackerbackend.Domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
}
