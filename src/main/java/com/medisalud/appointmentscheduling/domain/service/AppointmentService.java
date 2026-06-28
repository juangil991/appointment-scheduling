package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.domain.validator.AppointmentValidation;

import java.util.UUID;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidation appointmentValidation;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentValidation appointmentValidation) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidation = appointmentValidation;
    }

    public Appointment scheduleAppointment(Appointment appointment) {
        appointmentValidation.validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId);
        Appointment caceledAppointment = appointment.withStatus("CANCELADA");
        appointmentRepository.update(caceledAppointment);
        return caceledAppointment;
    }

}
