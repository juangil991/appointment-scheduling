package com.medisalud.appointmentscheduling.domain.exception;

public class AppointmentNotFoundException extends BusinessException{

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
