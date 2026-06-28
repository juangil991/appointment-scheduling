package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Doctor;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository {
    Doctor save(Doctor doctor);

    Optional<Doctor> findById(UUID id);
}
