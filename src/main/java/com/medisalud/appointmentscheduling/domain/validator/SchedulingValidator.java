package com.medisalud.appointmentscheduling.domain.validator;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import com.medisalud.appointmentscheduling.domain.exception.AppointmentNotFoundException;
import com.medisalud.appointmentscheduling.domain.exception.InvalidAppointmentScheduleException;
import com.medisalud.appointmentscheduling.domain.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class SchedulingValidator {

    private final DoctorRepository doctorRepository;

    public SchedulingValidator(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void validateAppointmentSchedule(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        validateDateRange(startDate, endDate);
        validateDoctorExists(doctorId);
    }

    public void validateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidAppointmentScheduleException(ErrorMessages.START_DATE_GREATER_THAN_END_DATE);
        }
    }

    public void validateDoctorExists(UUID doctorId) {
        if (doctorRepository.findById(doctorId).isEmpty()) {
            throw new AppointmentNotFoundException(ErrorMessages.DOCTOR_NOT_FOUND);
        }
    }

}
