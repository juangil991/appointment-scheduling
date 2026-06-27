package com.medisalud.appointmentscheduling.aplication.usecase;

import com.medisalud.appointmentscheduling.aplication.dto.DoctorRequest;
import com.medisalud.appointmentscheduling.aplication.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.aplication.mapper.DoctorMapper;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.service.DoctorService;
import org.springframework.stereotype.Component;

@Component
public class RegisterDoctorUseCase {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    public RegisterDoctorUseCase(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    public DoctorResponse registerDoctor(DoctorRequest request) {
        Doctor doctor = doctorMapper.toDomain(request);
        return doctorMapper.toResponse(doctorService.registerDoctor(doctor));
    }
}
