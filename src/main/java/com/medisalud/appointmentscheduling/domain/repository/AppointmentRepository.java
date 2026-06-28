package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Appointment;

import java.util.Optional;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);

}
