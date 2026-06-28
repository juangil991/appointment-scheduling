package com.medisalud.appointmentscheduling.infraestructure.database.entity;



import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "cancellation_date")
    private LocalDateTime cancellationDate;

    public AppointmentEntity(DoctorEntity doctor, PatientEntity patient, LocalDateTime appointmentDate, String status, LocalDateTime cancellationDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.cancellationDate = cancellationDate;
    }

    public UUID getId() {
        return id;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }
}
