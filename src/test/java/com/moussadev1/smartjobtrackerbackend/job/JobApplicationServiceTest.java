package com.moussadev1.smartjobtrackerbackend.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        JobApplication updatedApp = service.update(id, "New Title", "New Company");

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

    @Test
    void shouldUpdateStatus_WhenTransitionIsValid() {
        // Arrange
        UUID id = UUID.randomUUID();
        JobApplication existingJob = new JobApplication();
        existingJob.setId(id);
        existingJob.setStatus(ApplicationStatus.TO_APPLY);

        when(repository.findById(id)).thenReturn(Optional.of(existingJob));
        when(repository.save(any(JobApplication.class))).thenReturn(existingJob);

        // Act
        JobApplication updatedAppStatus = service.updateStatus(id, ApplicationStatus.APPLIED);

        // Assert
        assertThat(updatedAppStatus).isNotNull();
        assertThat(updatedAppStatus.getStatus()).isEqualTo(ApplicationStatus.APPLIED);

        verify(repository).findById(id);
        verify(repository).save(existingJob);
    }

    @Test
    void shouldNotUpdateStatus_WhenTransitionIsInvalid() {
        // Arrange
        UUID id = UUID.randomUUID();
        JobApplication existingJob = new JobApplication();
        existingJob.setId(id);
        existingJob.setStatus(ApplicationStatus.TO_APPLY);

        when(repository.findById(id)).thenReturn(Optional.of(existingJob));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            service.updateStatus(id, ApplicationStatus.OFFER);
        });

        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }
}
