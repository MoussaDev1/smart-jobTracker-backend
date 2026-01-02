package com.moussadev1.smartjobtrackerbackend.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository repository;

    public JobApplication create(String title, String company) {
        return repository.save(new JobApplication(title, company));
    }

    public JobApplication findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found with id: " + id));
    }

    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    public JobApplication update(UUID id, String title, String company) {
        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found with id: " + id));
        application.setTitle(title);
        application.setCompany(company);
        return repository.save(application);
    }

    public void deleteById(UUID id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Job Application not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public JobApplication updateStatus(UUID id, ApplicationStatus newStatus) {
        JobApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found with id: " + id));
        if(!application.getStatus().canTransitionTo(newStatus)){
            throw new IllegalStateException("Invalid status transition from " + application.getStatus() + " to " + newStatus);
        }
        application.setStatus(newStatus);
        return repository.save(application);
    }
}
