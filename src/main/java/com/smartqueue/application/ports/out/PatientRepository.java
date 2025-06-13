package com.smartqueue.application.ports.out;

import com.smartqueue.domain.model.Patient;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);

    Optional<Patient> findById(Long id);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByMedicalRecordNumber(String medicalRecordNumber);
}