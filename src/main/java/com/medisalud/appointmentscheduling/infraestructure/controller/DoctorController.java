package com.medisalud.appointmentscheduling.infraestructure.controller;

import com.medisalud.appointmentscheduling.application.dto.DoctorRequest;
import com.medisalud.appointmentscheduling.application.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.application.usecase.RegisterDoctorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    private final RegisterDoctorUseCase registerDoctorUseCase;

    public DoctorController(RegisterDoctorUseCase registerDoctorUseCase) {
        this.registerDoctorUseCase = registerDoctorUseCase;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> registerDoctor(@Valid @RequestBody DoctorRequest request) {
        DoctorResponse response = registerDoctorUseCase.registerDoctor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
