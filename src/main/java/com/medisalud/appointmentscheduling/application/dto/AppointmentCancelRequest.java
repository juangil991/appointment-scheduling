package com.medisalud.appointmentscheduling.application.dto;

import java.util.UUID;

public record AppointmentCancelRequest(UUID appointmentId) {
}
