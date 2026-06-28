package com.medisalud.appointmentscheduling.application.usecase;


import com.medisalud.appointmentscheduling.application.dto.AppointmentScheduleResponse;
import com.medisalud.appointmentscheduling.domain.model.ApointmentSchedule;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.service.AppointmentService;
import com.medisalud.appointmentscheduling.domain.service.SchedulingService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class CheckAppointmentScheduleUseCase {

    private final SchedulingService schedulingService;
    private final AppointmentService appointmentService;

    public CheckAppointmentScheduleUseCase(SchedulingService schedulingService, AppointmentService appointmentService) {
        this.schedulingService = schedulingService;
        this.appointmentService = appointmentService;
    }

    public List<AppointmentScheduleResponse> checkAvailableAppointmentSchedule(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<ApointmentSchedule> availableSchedules = schedulingService.getAppointmentSchedule(doctorId,startDate,endDate);
        return availableSchedules.stream()
                .map(schedule -> new AppointmentScheduleResponse(schedule.date(), schedule.timeSlots()))
                .sorted(Comparator.comparing(AppointmentScheduleResponse::date))
                .toList();
    }
}
