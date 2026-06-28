package com.medisalud.appointmentscheduling.application.usecase;

import com.medisalud.appointmentscheduling.application.dto.PatientRequest;
import com.medisalud.appointmentscheduling.application.dto.PatientResponse;
import com.medisalud.appointmentscheduling.application.mapper.PatientMapper;
import com.medisalud.appointmentscheduling.domain.service.PatientService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RegisterPatientUseCase {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public RegisterPatientUseCase(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @Transactional
    public PatientResponse registerPatient(PatientRequest request) {
        var patient = patientMapper.toDomain(request);
        return patientMapper.toResponse(patientService.registerPatient(patient));
    }
}

