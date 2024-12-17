package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

/**
 * @author Fabian Feichter
 */
@Configuration
public class TaskConfig {

    @Bean
    @Scope("prototype")
    public Task swag() {
        return new Task("SwagTask", LocalDateTime.now());
    }

    public record Task(String name, LocalDateTime timestamp) {
    }
}
