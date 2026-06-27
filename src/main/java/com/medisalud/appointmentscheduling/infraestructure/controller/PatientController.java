package com.medisalud.appointmentscheduling.infraestructure.controller;

import com.medisalud.appointmentscheduling.application.dto.PatientRequest;
import com.medisalud.appointmentscheduling.application.dto.PatientResponse;
import com.medisalud.appointmentscheduling.application.usecase.RegisterPatientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {

    private final RegisterPatientUseCase registerPatientUseCase;

    public PatientController(RegisterPatientUseCase registerPatientUseCase) {
        this.registerPatientUseCase = registerPatientUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> registerPatient(@Valid @RequestBody PatientRequest request) {
        PatientResponse response = registerPatientUseCase.registerPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
