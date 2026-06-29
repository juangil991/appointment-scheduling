package com.medisalud.appointmentscheduling.application.mapper;

import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentResponse toResponse(Appointment appointment) {

        return new AppointmentResponse(
                appointment.id(),
                appointment.patient().name(),
                appointment.doctor().name(),
                appointment.appointmentDate(),
                appointment.cancellationDate(),
                appointment.status(),
                null);
    }
}
