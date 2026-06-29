package com.medisalud.appointmentscheduling.domain.validator;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import com.medisalud.appointmentscheduling.domain.exception.InvalidAppointmentScheduleException;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.AppointmentFilter;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.domain.util.AppointmentScheduleUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class AppointmentValidation {

    private final AppointmentRepository appointmentRepository;

    public AppointmentValidation(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validateAppointment(Appointment appointment){
        validateAppointmentDateNotInPast(appointment.appointmentDate());
        validateAppointmentDate(appointment.appointmentDate());
        validateDuplicatedAppointment(appointment);
        validatePatientAppointment(appointment);
        validateBirthDay(appointment);

    }

    protected void validateAppointmentDateNotInPast(LocalDateTime appointmentDate) {
        if (appointmentDate.isBefore(LocalDateTime.now())) {
            throw new InvalidAppointmentScheduleException(ErrorMessages.APPOINTMENT_DATE_PAST);
        }
    }

    protected void validateAppointmentDate(LocalDateTime appointmentDate) {
        LocalTime appointmentTime = appointmentDate.toLocalTime();
        LocalDate appointmentLocalDate = appointmentDate.toLocalDate();
        List<LocalTime> validTime = AppointmentScheduleUtil.generateTimeSlots(appointmentLocalDate);
        if(!validTime.contains(appointmentTime)){
            throw new InvalidAppointmentScheduleException(ErrorMessages.APPOINTMENT_DATE_INVALID);
        }
    }

    protected void validateDuplicatedAppointment(Appointment appointment) {
        UUID doctorId = appointment.doctor().id();
        AppointmentFilter filter = new AppointmentFilter(doctorId,null,null,null,null);
        List<Appointment> bookedAppointments = appointmentRepository.findByFilter(filter);
        var bookedDates = bookedAppointments.stream().map(Appointment::appointmentDate).toList();
        if(bookedDates.contains(appointment.appointmentDate())){
            throw new InvalidAppointmentScheduleException(ErrorMessages.APPOINTMENT_DUPLICATED);
        }
    }

    protected void validatePatientAppointment(Appointment appointment) {
        UUID patientId = appointment.patient().id();
        AppointmentFilter filter = new AppointmentFilter(null,patientId,null,null,null);
        List<Appointment> bookedAppointments = appointmentRepository.findByFilter(filter);
        List<LocalDateTime> bookedDates = bookedAppointments.stream().map(Appointment::appointmentDate).toList();
        if(bookedDates.contains(appointment.appointmentDate())){
            throw new InvalidAppointmentScheduleException(ErrorMessages.PATIENT_APPOINTMENT_DUPLICATED);
        }
    }

    protected void validateBirthDay(Appointment appointment) {
        LocalDate birthDay = appointment.patient().birthDay();
        if(birthDay == null){return;}
        if (birthDay.isAfter(LocalDate.now())){
            throw new InvalidAppointmentScheduleException(ErrorMessages.FUTURE_BIRTH_DAY);
        }

    }
}
