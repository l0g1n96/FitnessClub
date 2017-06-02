package com.fitness.service;

import com.fitness.common.Scheduler;
import com.fitness.common.service.FitnessClubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaSpringFitnessConfig {

    @Bean
    public FitnessClubService fitnessClubService() {
        return new DefaultFitnessClubService();
    }

    @Bean
    public Scheduler scheduler() {
        return new FitnessScheduler();
    }
}
