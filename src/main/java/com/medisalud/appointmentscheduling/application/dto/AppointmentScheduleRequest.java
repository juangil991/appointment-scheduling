package com.medisalud.appointmentscheduling.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentScheduleRequest(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
}
