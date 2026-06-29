package com.medisalud.appointmentscheduling.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Appointment(UUID id, Patient patient, Doctor doctor, LocalDateTime appointmentDate, String status) {

    public Appointment withStatus(String status) {
        return new Appointment(
                id,
                patient,
                doctor,
                appointmentDate,
                status
        );
    }

    public Appointment withAppointmentDateAndStatus(LocalDateTime newDate, String status) {
        return new Appointment(
                null,
                patient,
                doctor,
                newDate,
                status
        );
    }
}
