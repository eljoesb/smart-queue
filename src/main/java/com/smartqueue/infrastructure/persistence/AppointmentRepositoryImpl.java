package com.smartqueue.infrastructure.persistence;

import com.smartqueue.application.ports.out.AppointmentRepository;
import com.smartqueue.domain.model.Appointment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Inject
    PanacheAppointmentRepository panacheRepository;

    @Override
    @Transactional
    public Appointment save(Appointment appointment) {
        panacheRepository.persist(appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return panacheRepository.findByIdOptional(id);
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return panacheRepository.find("patient.id", patientId).list();
    }

    @Override
    public List<Appointment> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return panacheRepository.find("scheduledTime between ?1 and ?2", start, end).list();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        panacheRepository.deleteById(id);
    }

    @Override
    public List<Appointment> findUpcomingAppointments(LocalDateTime now) {
        return panacheRepository.find("scheduledTime > ?1 and status != ?2",
                now,
                Appointment.AppointmentStatus.CANCELLED)
                .list();
    }

    @Override
    public List<Appointment> findOverdueAppointments(LocalDateTime now) {
        return panacheRepository.find("scheduledTime < ?1 and status = ?2",
                now,
                Appointment.AppointmentStatus.SCHEDULED)
                .list();
    }

    @ApplicationScoped
    static class PanacheAppointmentRepository implements PanacheRepository<Appointment> {
        // PanacheRepository implementation is handled automatically
    }
}