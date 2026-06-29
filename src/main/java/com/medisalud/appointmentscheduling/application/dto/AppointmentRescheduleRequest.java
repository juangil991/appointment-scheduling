package com.medisalud.appointmentscheduling.application.dto;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRescheduleRequest(
        @NotNull(message = ErrorMessages.APPOINTMENT_ID_REQUIRED)
        UUID appointmentId,
        @NotNull(message = ErrorMessages.RESCHEDULE_DATE_REQUIRED)
        LocalDateTime newAppointmentDate) {
}
