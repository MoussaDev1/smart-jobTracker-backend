package com.moussadev1.smartjobtrackerbackend.job.service;

import com.moussadev1.smartjobtrackerbackend.job.ApplicationStatus;
import com.moussadev1.smartjobtrackerbackend.job.JobApplication;
import com.moussadev1.smartjobtrackerbackend.job.JobApplicationRepository;
import com.moussadev1.smartjobtrackerbackend.job.JobApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobApplicationServiceTest {
    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    private JobApplication application;

    @BeforeEach
    public void setUp() {
        // Initialization if needed
         application = new JobApplication(
                "Software Engineer",
                "Tech Corp"
        );
    }
    @Test
    void shouldCreateJobApplication() {
        when(repository.save(any(JobApplication.class))).thenReturn(application);
        //Act
        JobApplication savedApp = service.create("Software Engineer", "Tech Corp");
        //Assert
        assertThat(savedApp).isNotNull();
        assertThat(savedApp.getTitle()).isEqualTo("Software Engineer");
        assertThat(savedApp.getCompany()).isEqualTo("Tech Corp");
        assertThat(savedApp.getStatus()).isEqualTo(application.getStatus());

        verify(repository).save((any(JobApplication.class)));
    }

    @Test
    void shouldFindJobApplicationById() {
        //Arrange
        UUID id = UUID.randomUUID();
        application.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(application));

        //Act
        JobApplication result = service.findById(id);

        //Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);

        verify(repository).findById(id);
    }

    @Test
    void shouldFindAllJobApplications() {
        //Act
        service.findAll();

        //Assert
        verify(repository).findAll();
    }

    @Test
    void shouldUpdateJobApplication() {
        //Arrange
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.of(application));
        when(repository.save(any(JobApplication.class))).thenReturn(application);

        //Act
        JobApplication updatedApp = service.update(id, "New Title", "New Company", ApplicationStatus.APPLIED);

        //Assert
        assertThat(updatedApp).isNotNull();
        assertThat(updatedApp.getTitle()).isEqualTo("New Title");
        assertThat(updatedApp.getCompany()).isEqualTo("New Company");
        assertThat(updatedApp.getStatus()).isEqualTo(application.getStatus());

        verify(repository).findById(id);
        verify(repository).save(application);
    }

    @Test
    void shouldDeleteJobApplication() {
        //Arrange
        UUID id = UUID.randomUUID();

        when(repository.existsById(id)).thenReturn(true);

        //Act
        service.deleteById(id);

        //Assert
        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }
}
