package com.medisalud.appointmentscheduling.infraestructure.controller;

import com.medisalud.appointmentscheduling.application.dto.AppointmentRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.dto.AppointmentScheduleRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentScheduleResponse;
import com.medisalud.appointmentscheduling.application.usecase.CheckAppointmentScheduleUseCase;
import com.medisalud.appointmentscheduling.application.usecase.ReserveAppointmentUseCase;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    private final ReserveAppointmentUseCase reserveAppointmentUseCase;
    private final CheckAppointmentScheduleUseCase checkAppointmentScheduleUseCase;

    public AppointmentController(ReserveAppointmentUseCase reserveAppointmentUseCase, CheckAppointmentScheduleUseCase checkAppointmentScheduleUseCase) {
        this.reserveAppointmentUseCase = reserveAppointmentUseCase;
        this.checkAppointmentScheduleUseCase = checkAppointmentScheduleUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> reserveAppointment(@Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse response = reserveAppointmentUseCase.reserveAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentScheduleResponse>> checkAppointmentSchedule(
            @PathVariable UUID doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<AppointmentScheduleResponse> response = checkAppointmentScheduleUseCase.checkAvailableAppointmentSchedule(doctorId,startDate,endDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
