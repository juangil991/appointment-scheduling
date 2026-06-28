package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Patient;

import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);

    Optional<Patient> findByIdentificationNumber(String id);
}
