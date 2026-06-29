package com.medisalud.appointmentscheduling.integration;

import java.time.LocalDateTime;
import java.util.UUID;

public record RescheduleAppointmentTest(String appointmentId, String newAppointmentDate) {
}
