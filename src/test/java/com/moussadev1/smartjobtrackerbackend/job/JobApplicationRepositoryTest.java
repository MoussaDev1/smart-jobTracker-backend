package com.moussadev1.smartjobtrackerbackend.job;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DataJpaTest
public class JobApplicationRepositoryTest {

    @Autowired
    private JobApplicationRepository repository;

    @Test
    void shouldSaveAndFindJobApplication() {
        JobApplication application = new JobApplication(
                "Software Engineer",
                "Tech Corp"
        );
        JobApplication savedApp = repository.save(application);

        assertThat(savedApp).isNotNull();
        assertThat(savedApp.getTitle()).isEqualTo("Software Engineer");
        assertThat(savedApp.getCompany()).isEqualTo("Tech Corp");
        assertThat(savedApp.getStatus()).isEqualTo(ApplicationStatus.TO_APPLY);
        assertThat(savedApp.getCreatedAt()).isCloseTo(LocalDateTime.now(), within(5, ChronoUnit.SECONDS));
    }
}
