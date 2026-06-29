package com.medisalud.appointmentscheduling.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Penalty(UUID id, UUID patientId, LocalDateTime createdAt) {
}
