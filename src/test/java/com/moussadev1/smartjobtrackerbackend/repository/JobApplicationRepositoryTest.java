package com.moussadev1.smartjobtrackerbackend.repository;

import com.moussadev1.smartjobtrackerbackend.Domain.ApplicationStatus;
import com.moussadev1.smartjobtrackerbackend.Domain.JobApplication;
import com.moussadev1.smartjobtrackerbackend.Repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository repository;

    @Test
    void shouldSaveAndFindJobApplication() {
        JobApplication application = new JobApplication(
                null,
                "Software Engineer",
                "Tech Corp",
                ApplicationStatus.APPLIED
        );
        JobApplication savedApp = repository.save(application);
        assertThat(savedApp.getId()).isNotNull();
        var foundApp = repository.findById(savedApp.getId()).orElse(null);

        assertThat(foundApp.getTitle()).isEqualTo("Software Engineer");
        assertThat(foundApp.getStatus()).isEqualTo(ApplicationStatus.APPLIED);

    }
}
