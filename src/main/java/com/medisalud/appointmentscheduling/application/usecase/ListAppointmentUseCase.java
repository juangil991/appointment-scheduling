package com.medisalud.appointmentscheduling.application.usecase;

import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.mapper.AppointmentMapper;
import com.medisalud.appointmentscheduling.domain.model.AppointmentFilter;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper mapper;

    public ListAppointmentUseCase(AppointmentRepository appointmentRepository, AppointmentMapper mapper) {
        this.appointmentRepository = appointmentRepository;
        this.mapper = mapper;
    }

    public List<AppointmentResponse> listAppointments(AppointmentFilter filter) {
        return appointmentRepository.findByFilter(filter).stream()
                .map(mapper::toResponse)
                .toList();

    }
}
