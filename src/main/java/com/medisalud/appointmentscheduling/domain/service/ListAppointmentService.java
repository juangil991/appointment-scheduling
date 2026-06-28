package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.AppointmentFilter;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;

import java.util.List;

public class ListAppointmentService {

    private final AppointmentRepository appointmentRepository;

    public ListAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> listAppointments(AppointmentFilter filter) {
        return appointmentRepository.findByFilter(filter);
    }


}
