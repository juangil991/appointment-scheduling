package com.medisalud.appointmentscheduling.domain.model;

public enum Specialty {
    CARDIOLOGY("Cardiología"),
    PEDIATRICS("Pediatría"),
    DERMATOLOGY("Dermatología"),
    NEUROLOGY("Neurología"),
    ORTHOPEDICS("Ortopedia"),
    OPHTHALMOLOGY("Oftalmología"),
    GYNECOLOGY("Ginecología"),
    UROLOGY("Urología"),
    PSYCHIATRY("Psiquiatría"),
    ONCOLOGY("Oncología");

    private final String description;

    Specialty(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
