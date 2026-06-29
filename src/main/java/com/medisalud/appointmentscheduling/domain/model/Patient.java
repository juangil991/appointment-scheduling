package com.medisalud.appointmentscheduling.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public record Patient(
        UUID id,
        String name,
        String identificationNumber,
        String phoneNumber,
        String email,
        LocalDate birthDay ) { }
