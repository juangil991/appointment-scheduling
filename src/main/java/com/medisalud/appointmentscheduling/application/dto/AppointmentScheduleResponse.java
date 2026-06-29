package com.medisalud.appointmentscheduling.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AppointmentScheduleResponse(
        @JsonProperty("Fecha")
        LocalDate date,
        @JsonProperty("HorariosDisponibles")
        List<LocalTime> timeSlots) {
}
