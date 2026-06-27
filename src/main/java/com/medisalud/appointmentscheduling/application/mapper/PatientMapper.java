package com.medisalud.appointmentscheduling.application.mapper;


import com.medisalud.appointmentscheduling.application.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.application.dto.PatientRequest;
import com.medisalud.appointmentscheduling.application.dto.PatientResponse;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient toDomain(PatientRequest request) {

        return new Patient(
                null,
                request.name(),
                request.identificationNumber(),
                request.phoneNumber(),
                request.email());
    }

    public PatientResponse toResponse(Patient patient) {

        return new PatientResponse(
                patient.id(),
                patient.name(),
                patient.identificationNumber(),
                patient.phoneNumber(),
                patient.email(),
                null);
    }
}
