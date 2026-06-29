package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.Penalty;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.domain.repository.PenaltyRepository;
import com.medisalud.appointmentscheduling.domain.validator.AppointmentValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentValidation appointmentValidation;
    private final PenaltyRepository penaltyRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentValidation appointmentValidation, PenaltyRepository penaltyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentValidation = appointmentValidation;
        this.penaltyRepository = penaltyRepository;
    }

    public Appointment scheduleAppointment(Appointment appointment) {
        appointmentValidation.validateAppointment(appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId);
        Appointment caceledAppointment = appointment.withStatus("CANCELADA");
        createPenalty(caceledAppointment);
        appointmentRepository.update(caceledAppointment);
        return caceledAppointment;
    }

    public Appointment rescheduleAppointment(UUID appointmentId, LocalDateTime newDate) {
        Appointment appointment = cancelAppointment(appointmentId);
        Appointment rescheduledAppointment = appointment.withAppointmentDateAndStatus(newDate, "PROGRAMADA");
        return scheduleAppointment(rescheduledAppointment);
    }

    protected void createPenalty(Appointment appointment) {
        if(LocalDateTime.now().isAfter(appointment.appointmentDate().minusHours(2))) {
            penaltyRepository.save(new Penalty(null, appointment.patient().id(), LocalDateTime.now()));
        }
    }

}
