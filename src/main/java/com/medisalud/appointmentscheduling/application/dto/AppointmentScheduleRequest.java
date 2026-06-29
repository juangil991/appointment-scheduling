package com.medisalud.appointmentscheduling.application.dto;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentScheduleRequest(
        @NotNull(message = ErrorMessages.DOCTOR_ID_REQUIRED)
        UUID doctorId,
        @NotNull(message = ErrorMessages.START_DATE_REQUIRED)
        LocalDateTime startDate,
        @NotNull(message = ErrorMessages.END_DATE_REQUIRED)
        LocalDateTime endDate) {
}
