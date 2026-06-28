package com.medisalud.appointmentscheduling.application.usecase;

import com.medisalud.appointmentscheduling.application.dto.AppointmentCancelRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.mapper.AppointmentMapper;
import com.medisalud.appointmentscheduling.domain.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CancelAppointmentUseCase {

    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;

    public CancelAppointmentUseCase(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @Transactional
    public AppointmentResponse cancelAppointment(AppointmentCancelRequest request) {
       return mapper.toResponse(appointmentService.cancelAppointment(request.appointmentId()));
    }
}
