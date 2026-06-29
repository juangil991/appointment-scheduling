package com.medisalud.appointmentscheduling.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRescheduleRequest(UUID appointmentId, LocalDateTime newAppointmentDate) {
}
