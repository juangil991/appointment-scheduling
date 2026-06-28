package com.medisalud.appointmentscheduling.domain.validator;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import com.medisalud.appointmentscheduling.domain.exception.InvalidAppointmentScheduleException;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.util.AppointmentScheduleUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AppointmentValidation {

    public void validateAppointment(Appointment appointment){
       validateAppointmentDate(appointment.appointmentDate());
    }

    protected void validateAppointmentDate(LocalDateTime appointmentDate) {
        LocalTime appointmentTime = appointmentDate.toLocalTime();
        LocalDate appointmentLocalDate = appointmentDate.toLocalDate();
        List<LocalTime> validTime = AppointmentScheduleUtil.generateTimeSlots(appointmentLocalDate);
        if(!validTime.contains(appointmentTime)){
            throw new InvalidAppointmentScheduleException(ErrorMessages.APPOINTMENT_DATE_INVALID);
        }
    }
}
