package com.medisalud.appointmentscheduling.infraestructure.database.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String identificationNumber;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    public PatientEntity() {
    }

    public PatientEntity(String name, String identificationNumber, String phone, String email) {
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.phone = phone;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

