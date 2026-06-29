package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentRequest(
        @NotBlank(message = ErrorMessages.PATIENT_ID_REQUIRED)
        String patientIdentification,
        @NotNull(message = ErrorMessages.DOCTOR_ID_REQUIRED)
        UUID doctorId,
        @NotBlank(message = ErrorMessages.APPOINTMENT_DATETIME_REQUIRED)
        String appointmentDate) {
}
