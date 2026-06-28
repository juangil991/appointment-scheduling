package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment scheduleAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId);
        Appointment caceledAppointment = appointment.withStatus("CANCELADA");
        appointmentRepository.update(caceledAppointment);
        return caceledAppointment;
    }

    public List<Appointment> findAppointmentByDoctorIdAndDateRange(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findByDoctorAndDateRange(doctorId, startDate, endDate);
    }
}
