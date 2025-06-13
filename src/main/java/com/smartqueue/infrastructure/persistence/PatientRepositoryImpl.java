package com.smartqueue.infrastructure.persistence;

import com.smartqueue.application.ports.out.PatientRepository;
import com.smartqueue.domain.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class PatientRepositoryImpl implements PatientRepository {

    @Inject
    PanachePatientRepository panacheRepository;

    @Override
    @Transactional
    public Patient save(Patient patient) {
        panacheRepository.persist(patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return panacheRepository.findByIdOptional(id);
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        return panacheRepository.find("email", email).firstResultOptional();
    }

    @Override
    public Optional<Patient> findByMedicalRecordNumber(String medicalRecordNumber) {
        return panacheRepository.find("medicalRecordNumber", medicalRecordNumber).firstResultOptional();
    }

    @ApplicationScoped
    static class PanachePatientRepository implements PanacheRepository<Patient> {
        // PanacheRepository implementation is handled automatically
    }
}