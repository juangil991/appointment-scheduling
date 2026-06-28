package com.medisalud.appointmentscheduling.domain.model;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ApointmentSchedule(LocalDate date, List<LocalTime> timeSlots) {
}
