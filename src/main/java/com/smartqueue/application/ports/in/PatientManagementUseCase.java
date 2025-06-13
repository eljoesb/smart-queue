package com.smartqueue.application.ports.in;

import com.smartqueue.domain.model.Patient;

public interface PatientManagementUseCase {
    Patient createPatient(Patient patient);

    Patient getPatient(Long id);
}