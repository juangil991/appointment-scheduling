package com.medisalud.appointmentscheduling.domain.service;

import com.medisalud.appointmentscheduling.domain.model.ApointmentSchedule;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.domain.util.AppointmentScheduleUtil;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SchedulingService {

    private final AppointmentRepository appointmentRepository;

    public SchedulingService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<ApointmentSchedule> getAppointmentSchedule(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Appointment> appointments = appointmentRepository.findByDoctorAndDateRange(doctorId, startDate, endDate);
        List<LocalDateTime> doctorAppointmentDates = appointments.stream().map(Appointment::appointmentDate).toList();
        List<ApointmentSchedule> doctorAppointmentSchedules = getDoctorAppointmentSchedule(doctorAppointmentDates);
        List<LocalDateTime> dateRange = generateDateRange(startDate, endDate);
        List<ApointmentSchedule> availableAppointments = getAppointmentSchedule(dateRange);
        return availableAppointments.stream().map(availableSchedule -> {
            ApointmentSchedule doctorSchedule = doctorAppointmentSchedules.stream()
                    .filter(schedule -> schedule.date().equals(availableSchedule.date()))
                    .findFirst()
                    .orElse(new ApointmentSchedule(availableSchedule.date(), Collections.emptyList()));

            List<LocalTime> availableSlots = availableSchedule.timeSlots().stream()
                    .filter(slot -> !doctorSchedule.timeSlots().contains(slot))
                    .toList();

            return new ApointmentSchedule(availableSchedule.date(), availableSlots);
        }).toList();
    }

    protected  List<LocalDateTime> generateDateRange(LocalDateTime startDate, LocalDateTime endDate) {

        LocalDateTime start = startDate.toLocalDate().atStartOfDay();
        LocalDateTime end = endDate.toLocalDate().atTime(LocalTime.MAX);

        List<LocalDateTime> dates = new ArrayList<>();

        for (LocalDateTime current = start;
             !current.isAfter(end);
             current = current.plusDays(1)) {
            dates.add(current);
        }

        return dates;
    }

    protected List<ApointmentSchedule> getDoctorAppointmentSchedule(List<LocalDateTime> doctorAppointmentsDate){
        var appointmentsDate = doctorAppointmentsDate.stream().collect(Collectors.groupingBy(LocalDateTime::toLocalDate));
        return appointmentsDate.entrySet().stream().map(entry -> {
            LocalDate date = entry.getKey();
            List<LocalTime> slots = entry.getValue().stream().map(LocalDateTime::toLocalTime).toList();
            return new ApointmentSchedule(date, slots);
        }).toList();
    }

    protected List<ApointmentSchedule> getAppointmentSchedule(List<LocalDateTime> dateRange) {
        return dateRange.stream().map(dateTime -> {
            LocalDate date = dateTime.toLocalDate();
            List<LocalTime> slots = AppointmentScheduleUtil.generateTimeSlots(date);
            return new ApointmentSchedule(date, slots);
        }).toList();
    }

}
