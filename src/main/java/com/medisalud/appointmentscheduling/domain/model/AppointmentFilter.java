package com.medisalud.appointmentscheduling.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentFilter(UUID doctorId,UUID patientId,String status,LocalDateTime startDate, LocalDateTime endDate) {
    public AppointmentFilter(UUID doctorId) {
        this(doctorId, null, null, null, null);
    }

}
