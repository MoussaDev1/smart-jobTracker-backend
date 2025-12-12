package com.moussadev1.smartjobtrackerbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {
    public record HealthStatus(String status) {}

    @GetMapping("/health")
    public HealthStatus status(){
        return new HealthStatus("ok");
    }

}
