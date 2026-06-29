package com.medisalud.appointmentscheduling.application.dto;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientRequest(
        @NotBlank(message = ErrorMessages.NAME_REQUIRED)
        @Size(min = 3, max = 100, message = ErrorMessages.NAME_INVALID)
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ. ]+$", message = ErrorMessages.NAME_FORMAT_INVALID)
        String name,

        @NotBlank(message = ErrorMessages.DOCUMENT_NUMBER_REQUIRED)
        @Size(min = 7, message = ErrorMessages.DOCUMENT_NUMBER_INVALID)
        String identificationNumber,

        @NotBlank(message = ErrorMessages.PHONE_NUMBER_REQUIRED)
        @Size(min = 7, message = ErrorMessages.PATIENT_PHONE_NUMBER_INVALID)
        String phoneNumber,

        @NotBlank(message = ErrorMessages.EMAIL_REQUIRED)
        @Email(message = ErrorMessages.EMAIL_INVALID)
        String email,

        LocalDate birthDay) {}
