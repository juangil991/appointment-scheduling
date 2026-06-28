package com.medisalud.appointmentscheduling.infraestructure.controller;

import com.medisalud.appointmentscheduling.application.dto.AppointmentRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.usecase.ReserveAppointmentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    private final ReserveAppointmentUseCase reserveAppointmentUseCase;

    public AppointmentController(ReserveAppointmentUseCase reserveAppointmentUseCase) {
        this.reserveAppointmentUseCase = reserveAppointmentUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> reserveAppointment(@Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse response = reserveAppointmentUseCase.reserveAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
