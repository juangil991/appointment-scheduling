package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment scheduleAppointment(Appointment appointment) {

        return appointmentRepository.save(appointment);
    }
}
