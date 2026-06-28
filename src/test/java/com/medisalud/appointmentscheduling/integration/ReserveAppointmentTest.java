package com.medisalud.appointmentscheduling.integration;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReserveAppointmentTest(String patientIdentification, String doctorId, String appointmentDate) {
}
