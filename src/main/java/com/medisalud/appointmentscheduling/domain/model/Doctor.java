package com.medisalud.appointmentscheduling.domain.model;

import java.util.UUID;

public record Doctor(String name, String specialty, String phoneNumber, String email) {
}
