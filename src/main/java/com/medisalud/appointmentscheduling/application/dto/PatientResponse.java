package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PatientResponse(
        @JsonProperty("Id")
        UUID id,
        @JsonProperty("Nombre")
        String name,
        @JsonProperty("Identificación")
        String identificationNumber,
        @JsonProperty("Teléfono")
        String phoneNumber,
        @JsonProperty("Email")
        String email,
        @JsonProperty("Mensaje")
        String message,
        @JsonProperty("Fecha de nacimiento")
        LocalDate birthDate
) {

        public PatientResponse(String message) {
                this(null, null, null, null, null, message,null);
        }
}
