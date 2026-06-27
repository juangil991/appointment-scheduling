package com.medisalud.appointmentscheduling.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PatientRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ. ]+$", message = "Nombre inválido")
        String name,

        @NotBlank(message = "El documento de identidad es obligatorio.")
        @Size(min = 7, message = "El documento de identidad debe de tener minimo 7 digitos")
        String identificationNumber,

        @NotBlank(message = "El teléfono es obligatorio.")
        @Size(min = 7, message = "El número de teléfono debe de tener minimo 7 digitos")
        String phoneNumber,

        @NotBlank(message = "El email es obligatorio.")
        @Email(message = "Formato de email invalido")
        String email) {
}
