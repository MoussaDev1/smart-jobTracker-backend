package com.moussadev1.smartjobtrackerbackend.job;

import com.moussadev1.smartjobtrackerbackend.job.dto.CreateJobApplicationRequestDTO;
import com.moussadev1.smartjobtrackerbackend.job.dto.JobApplicationResponseDTO;
import com.moussadev1.smartjobtrackerbackend.job.dto.UpdateJobApplicationRequestDTO;
import com.moussadev1.smartjobtrackerbackend.job.dto.mapper.JobApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobApplicationController {

    @Autowired
    JobApplicationService service;

    @PostMapping("/job")
    public ResponseEntity<JobApplicationResponseDTO> createJobApplication(@RequestBody CreateJobApplicationRequestDTO request) {
        JobApplication jobApplication = service.create(request.title(), request.company());
        return ResponseEntity.ok(JobApplicationMapper.toResponseDTO(jobApplication));
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobApplicationResponseDTO> getJobApplicationById(@PathVariable UUID id) {
        JobApplication jobApplication = service.findById(id);
        return ResponseEntity.ok(JobApplicationMapper.toResponseDTO(jobApplication));
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobApplicationResponseDTO>> getAllJobApplications() {
        List<JobApplicationResponseDTO> jobApplications = service.findAll()
                .stream()
                .map(JobApplicationMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(jobApplications);
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<JobApplicationResponseDTO> updateJobApplication(@PathVariable UUID id, @RequestBody UpdateJobApplicationRequestDTO UpdateRequest) {
        JobApplication updatedJobApplication = service.update(id, UpdateRequest.title(), UpdateRequest.company(), UpdateRequest.status());
        return ResponseEntity.ok(JobApplicationMapper.toResponseDTO(updatedJobApplication));
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
