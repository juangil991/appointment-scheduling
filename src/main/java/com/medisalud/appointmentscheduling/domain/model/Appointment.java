package com.medisalud.appointmentscheduling.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Appointment(UUID id, Patient patient, Doctor doctor, LocalDateTime appointmentDate, String status) {
}
