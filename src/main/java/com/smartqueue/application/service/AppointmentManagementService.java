package com.smartqueue.application.service;

import com.smartqueue.application.ports.in.AppointmentManagementUseCase;
import com.smartqueue.application.ports.out.AppointmentRepository;
import com.smartqueue.application.ports.out.AIPredictionService;
import com.smartqueue.domain.model.Appointment;
import com.smartqueue.domain.model.Patient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class AppointmentManagementService implements AppointmentManagementUseCase {

    private final AppointmentRepository appointmentRepository;
    private final AIPredictionService aiPredictionService;

    @Inject
    public AppointmentManagementService(AppointmentRepository appointmentRepository,
            AIPredictionService aiPredictionService) {
        this.appointmentRepository = appointmentRepository;
        this.aiPredictionService = aiPredictionService;
    }

    @Override
    @Transactional
    public Appointment scheduleAppointment(Long patientId, LocalDateTime preferredTime) {
        // Get optimal appointment time using AI prediction
        LocalDateTime optimalTime = aiPredictionService.predictOptimalAppointmentTime(preferredTime);

        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        patient.setId(patientId);
        appointment.setPatient(patient);
        appointment.setScheduledTime(optimalTime);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);

        // Predict and set wait time
        int estimatedWaitTime = aiPredictionService.predictWaitTime(appointment);
        appointment.setEstimatedWaitTime(estimatedWaitTime);

        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment rescheduleAppointment(Long appointmentId, LocalDateTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setScheduledTime(newTime);
        appointment.setStatus(Appointment.AppointmentStatus.RESCHEDULED);

        // Update wait time prediction
        int estimatedWaitTime = aiPredictionService.predictWaitTime(appointment);
        appointment.setEstimatedWaitTime(estimatedWaitTime);

        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getUpcomingAppointments() {
        return appointmentRepository.findUpcomingAppointments(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void updateAppointmentStatus(Long appointmentId, Appointment.AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    @Override
    public int getEstimatedWaitTime(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return aiPredictionService.predictWaitTime(appointment);
    }
}