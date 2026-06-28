package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Patient;
import com.medisalud.appointmentscheduling.domain.repository.PatientRepository;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientByIdentificationNumber(String id) {
        return patientRepository.findByIdentificationNumber(id).orElse(null);
    }
}
