package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);

    List<Appointment> findByDoctorAndDateRange(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate);

}
