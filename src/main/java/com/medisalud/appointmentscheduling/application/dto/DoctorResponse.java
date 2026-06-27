package com.medisalud.appointmentscheduling.aplication.dto;

import java.util.UUID;

public class DoctorResponse {
    private UUID id;
    private String name;
    private String specialty;
    private String phoneNumber;
    private String email;

    public DoctorResponse(UUID id, String name, String specialty, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
