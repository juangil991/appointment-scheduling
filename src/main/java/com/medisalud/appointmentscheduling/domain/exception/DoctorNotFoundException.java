package com.medisalud.appointmentscheduling.domain.exception;

public class DoctorNotFoundException extends BusinessException{

    public DoctorNotFoundException(String message) {
        super(message);
    }
}
