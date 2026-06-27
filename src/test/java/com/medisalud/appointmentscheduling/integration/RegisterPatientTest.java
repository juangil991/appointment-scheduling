package com.medisalud.appointmentscheduling.integration;

import java.util.UUID;

public record RegisterPatientTest(
        String name,
        String identificationNumber,
        String phoneNumber,
        String email) { }
