package com.smartqueue.infrastructure.service;

import com.smartqueue.application.ports.out.AIPredictionService;
import com.smartqueue.domain.model.Appointment;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.Random;

@ApplicationScoped
public class MockAIPredictionService implements AIPredictionService {
    private final Random random = new Random();

    @Override
    public int predictWaitTime(Appointment appointment) {
        // Mock implementation: returns a random wait time between 5 and 30 minutes
        return 5 + random.nextInt(26);
    }

    @Override
    public LocalDateTime predictOptimalAppointmentTime(LocalDateTime preferredTime) {
        // Mock implementation: returns the preferred time as is
        return preferredTime;
    }

    @Override
    public double predictNoShowProbability(Appointment appointment) {
        // Mock implementation: returns a random probability between 0 and 0.3
        return random.nextDouble() * 0.3;
    }
}