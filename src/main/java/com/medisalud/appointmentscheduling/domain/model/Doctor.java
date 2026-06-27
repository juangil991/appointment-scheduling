package com.medisalud.appointmentscheduling.domain.model;

import java.util.UUID;

public record Doctor(UUID id, String name, String specialty, String phoneNumber, String email) {
}
