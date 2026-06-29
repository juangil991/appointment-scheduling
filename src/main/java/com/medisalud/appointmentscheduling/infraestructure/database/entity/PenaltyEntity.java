package com.medisalud.appointmentscheduling.infraestructure.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "penalties")
public class PenaltyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public PenaltyEntity() {
    }

    public PenaltyEntity(PatientEntity patient, LocalDateTime createdAt) {
        this.patient = patient;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
