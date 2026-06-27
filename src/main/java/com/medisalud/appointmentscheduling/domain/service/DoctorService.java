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

    public Doctor registerDoctor(String name, String specialty, String phoneNumber, String email) {
        return doctorRepository.save(new Doctor(name, specialty, phoneNumber, email));
    }


}
