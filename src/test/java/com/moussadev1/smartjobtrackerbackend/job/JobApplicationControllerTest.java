package com.moussadev1.smartjobtrackerbackend.job;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class JobApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @BeforeEach
    void cleanDatabase() {
        jobApplicationRepository.deleteAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoJobApplications() throws Exception {
        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void ShouldReturnJobApplicationsList() throws Exception {
        JobApplication job1 = new JobApplication("Software Engineer", "Tech Corp");
        JobApplication job2 = new JobApplication("Data Analyst", "Data Inc");
        jobApplicationRepository.save(job1);
        jobApplicationRepository.save(job2);

        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'title':'Software Engineer','company':'Tech Corp'},{'title':'Data Analyst','company':'Data Inc'}]"));
    }
}
