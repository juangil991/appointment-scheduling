package com.medisalud.appointmentscheduling.infraestructure.exception;

import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.domain.exception.InvalidAppointmentScheduleException;
import com.medisalud.appointmentscheduling.infraestructure.controller.AppointmentController;
import com.medisalud.appointmentscheduling.infraestructure.controller.DoctorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AppointmentController.class)
public class AppointmentExceptionAdvice {

    @ExceptionHandler(InvalidAppointmentScheduleException.class)
    public ResponseEntity<AppointmentResponse> handleInvalidAppointmentScheduleException(InvalidAppointmentScheduleException exception) {
        AppointmentResponse response = new AppointmentResponse(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
