package com.medisalud.appointmentscheduling.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DoctorRequest(
        @NotBlank(message = "El nombre es obligatorio.")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ. ]+$", message = "Nombre inválido")
        String name,
        @NotBlank(message = "La especialidad es obligatoria")
        String specialty,
        @Pattern(regexp = "^(\\d{7}|\\d{10})$", message = "El teléfono debe contener exactamente 7 o 10 dígitos.")
        String phoneNumber,
        @Email(message = "Formato de email invalido")
        String email) {
}
