package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentResponse(
        @JsonProperty("Id")
        UUID id,
        @JsonProperty("Paciente")
        String patient,
        @JsonProperty("Médico")
        String Doctor,
        @JsonProperty("Fecha")
        LocalDateTime appointmentDate,
        @JsonProperty("Fecha de cancelación")
        LocalDateTime cancellationDate,
        @JsonProperty("Estado")
        String status,
        @JsonProperty("Mensaje")
        String message) {

        public AppointmentResponse(String message) {
                this(null, null, null, null, null, null,message);
        }
}
