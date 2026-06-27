package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Patient;

public interface PatientRepository {
    Patient save(Patient patient);
}
