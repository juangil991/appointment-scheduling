package com.medisalud.appointmentscheduling.infraestructure.controller;

import com.medisalud.appointmentscheduling.application.dto.*;
import com.medisalud.appointmentscheduling.application.usecase.CancelAppointmentUseCase;
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
    private final CancelAppointmentUseCase appointmentUseCase;

    public AppointmentController(ReserveAppointmentUseCase reserveAppointmentUseCase, CheckAppointmentScheduleUseCase checkAppointmentScheduleUseCase, CancelAppointmentUseCase appointmentUseCase) {
        this.reserveAppointmentUseCase = reserveAppointmentUseCase;
        this.checkAppointmentScheduleUseCase = checkAppointmentScheduleUseCase;
        this.appointmentUseCase = appointmentUseCase;
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

    @PutMapping("/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(@RequestBody @Valid AppointmentCancelRequest request) {
        AppointmentResponse response = appointmentUseCase.cancelAppointment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
