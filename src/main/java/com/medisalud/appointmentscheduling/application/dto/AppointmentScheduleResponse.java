package com.medisalud.appointmentscheduling.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AppointmentScheduleResponse(LocalDate date, List<LocalTime> timeSlots) {
}
