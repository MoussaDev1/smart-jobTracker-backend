package com.moussadev1.smartjobtrackerbackend.job.dto.mapper;

import com.moussadev1.smartjobtrackerbackend.job.JobApplication;
import com.moussadev1.smartjobtrackerbackend.job.dto.CreateJobApplicationRequestDTO;
import com.moussadev1.smartjobtrackerbackend.job.dto.CreateJobApplicationResponseDTO;

public class JobApplicationMapper {

    public static JobApplication toEntity(CreateJobApplicationRequestDTO dto){
        JobApplication jobApplication = new JobApplication();
        jobApplication.setCompany(dto.company());
        jobApplication.setTitle(dto.title());
        return jobApplication;
    }

    public static CreateJobApplicationResponseDTO toResponseDTO(JobApplication jobApplication){
        return new CreateJobApplicationResponseDTO(
                jobApplication.getId(),
                jobApplication.getTitle(),
                jobApplication.getCompany(),
                jobApplication.getStatus(),
                jobApplication.getCreatedAt()
        );
    }
}
