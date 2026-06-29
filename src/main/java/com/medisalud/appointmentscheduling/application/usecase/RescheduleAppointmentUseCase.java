package com.medisalud.appointmentscheduling.application.usecase;

import com.medisalud.appointmentscheduling.application.dto.AppointmentRescheduleRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.mapper.AppointmentMapper;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.service.AppointmentService;
import org.springframework.stereotype.Component;

@Component
public class RescheduleAppointmentUseCase {

    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;


    public RescheduleAppointmentUseCase(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    public AppointmentResponse rescheduleAppointment(AppointmentRescheduleRequest request) {
        Appointment rescheduleAppointment =appointmentService.rescheduleAppointment(request.appointmentId(), request.newAppointmentDate());
        return mapper.toResponse(rescheduleAppointment);
    }
}
