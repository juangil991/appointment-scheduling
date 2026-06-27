package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DoctorResponse(
        @JsonProperty("Id")
        UUID id,
        @JsonProperty("Nombre")
        String name,
        @JsonProperty("Especialidad")
        String specialty,
        @JsonProperty("Teléfono")
        String phoneNumber,
        @JsonProperty("Email")
        String email,
        @JsonProperty("Mensaje")
        String message) {

        public DoctorResponse(String message) {
            this(null, null, null, null, null, message);
        }
}

