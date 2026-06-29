package com.medisalud.appointmentscheduling.application.dto;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record AppointmentCancelRequest(
        @NotNull(message = ErrorMessages.APPOINTMENT_DATE_REQUIRED)
        UUID appointmentId) {
}
