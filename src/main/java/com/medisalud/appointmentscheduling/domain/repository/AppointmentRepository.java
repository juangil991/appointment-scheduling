package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.AppointmentFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);

    void update(Appointment appointment);

    Appointment findById(UUID appointmentId);

    List<Appointment> findByDoctorAndDateRange(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> findByFilter(AppointmentFilter filter);
}
