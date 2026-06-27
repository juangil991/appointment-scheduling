package com.medisalud.appointmentscheduling.aplication.mapper;

import com.medisalud.appointmentscheduling.aplication.dto.DoctorRequest;
import com.medisalud.appointmentscheduling.aplication.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toDomain(DoctorRequest request) {

        return new Doctor(
                null,
                request.name(),
                request.specialty(),
                request.phoneNumber(),
                request.email());
    }

    public DoctorResponse toResponse(Doctor doctor) {

        return new DoctorResponse(
                doctor.id(),
                doctor.name(),
                doctor.specialty(),
                doctor.phoneNumber(),
                doctor.email());
    }
}
