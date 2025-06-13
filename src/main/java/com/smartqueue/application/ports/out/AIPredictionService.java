package com.smartqueue.application.ports.out;

import com.smartqueue.domain.model.Appointment;
import java.time.LocalDateTime;

public interface AIPredictionService {
    int predictWaitTime(Appointment appointment);

    LocalDateTime predictOptimalAppointmentTime(LocalDateTime preferredTime);

    double predictNoShowProbability(Appointment appointment);
}