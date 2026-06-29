package com.medisalud.appointmentscheduling.domain.exception;

public class AppointmentConflictException extends BusinessException{

    public AppointmentConflictException(String message) {
        super(message);
    }
}
