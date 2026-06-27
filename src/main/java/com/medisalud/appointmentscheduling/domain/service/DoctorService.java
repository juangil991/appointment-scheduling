package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.repository.DoctorRepository;
import com.medisalud.appointmentscheduling.domain.validator.ValidateDoctor;

public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ValidateDoctor validateDoctor;

    public DoctorService(DoctorRepository doctorRepository, ValidateDoctor validateDoctor) {
        this.doctorRepository = doctorRepository;
        this.validateDoctor = validateDoctor;
    }

    public Doctor registerDoctor(Doctor doctor) {
        validateDoctor.validateDoctor(doctor);
        return doctorRepository.save(doctor);
    }


}
