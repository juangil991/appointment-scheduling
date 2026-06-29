package com.medisalud.appointmentscheduling.application.dto;


import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record DoctorRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ. ]+$", message = "Nombre inválido")
        String name,
        @NotBlank(message = ErrorMessages.SPECIALTY_REQUIRED)
        String specialty,
        @Pattern(regexp = "^(\\d{7}|\\d{10})$", message = ErrorMessages.PHONE_NUMBER_INVALID)
        String phoneNumber,
        @Email(message = "Formato de email invalido")
        String email,
        LocalDate birthDay) {
}
