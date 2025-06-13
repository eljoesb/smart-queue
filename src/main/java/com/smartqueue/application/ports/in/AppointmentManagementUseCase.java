package com.smartqueue.application.ports.in;

import com.smartqueue.domain.model.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentManagementUseCase {
    Appointment scheduleAppointment(Long patientId, LocalDateTime preferredTime);

    Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newTime);

    void cancelAppointment(Long appointmentId);

    Appointment getAppointment(Long appointmentId);

    List<Appointment> getPatientAppointments(Long patientId);

    List<Appointment> getUpcomingAppointments();

    void updateAppointmentStatus(Long appointmentId, Appointment.AppointmentStatus status);

    int getEstimatedWaitTime(Long appointmentId);
}