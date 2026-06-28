package com.medisalud.appointmentscheduling.domain.exception;

public class InvalidAppointmentScheduleException extends RuntimeException {
    public InvalidAppointmentScheduleException(String message) {
        super(message);
    }
}
