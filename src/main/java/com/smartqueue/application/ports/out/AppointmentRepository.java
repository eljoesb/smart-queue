package com.smartqueue.application.ports.out;

import com.smartqueue.domain.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Optional<Appointment> findById(Long id);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDateRange(LocalDateTime start, LocalDateTime end);

    void delete(Long id);

    List<Appointment> findUpcomingAppointments(LocalDateTime now);

    List<Appointment> findOverdueAppointments(LocalDateTime now);
}