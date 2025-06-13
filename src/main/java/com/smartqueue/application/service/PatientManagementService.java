package com.smartqueue.application.service;

import com.smartqueue.application.ports.in.PatientManagementUseCase;
import com.smartqueue.application.ports.out.PatientRepository;
import com.smartqueue.domain.model.Patient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PatientManagementService implements PatientManagementUseCase {

    private final PatientRepository patientRepository;

    @Inject
    public PatientManagementService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional
    public Patient createPatient(Patient patient) {
        // Check if patient with same email already exists
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new RuntimeException("Patient with this email already exists");
        }

        // Check if patient with same medical record number already exists
        if (patient.getMedicalRecordNumber() != null &&
                patientRepository.findByMedicalRecordNumber(patient.getMedicalRecordNumber()).isPresent()) {
            throw new RuntimeException("Patient with this medical record number already exists");
        }

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}