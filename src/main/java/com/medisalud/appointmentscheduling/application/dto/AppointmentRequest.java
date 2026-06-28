package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AppointmentRequest(String patientIdentification, UUID doctorId, String appointmentDate) {
}
