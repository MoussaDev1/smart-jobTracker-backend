package com.moussadev1.smartjobtrackerbackend.job.dto.mapper;

import com.moussadev1.smartjobtrackerbackend.job.JobApplication;
import com.moussadev1.smartjobtrackerbackend.job.dto.JobApplicationResponseDTO;

public class JobApplicationMapper {

    public static JobApplicationResponseDTO toResponseDTO(JobApplication jobApplication){
        return new JobApplicationResponseDTO(
                jobApplication.getId(),
                jobApplication.getTitle(),
                jobApplication.getCompany(),
                jobApplication.getStatus(),
                jobApplication.getCreatedAt()
        );
    }
}
