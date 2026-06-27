package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Doctor;

public interface DoctorRepository {
    Doctor save(Doctor doctor);
}
