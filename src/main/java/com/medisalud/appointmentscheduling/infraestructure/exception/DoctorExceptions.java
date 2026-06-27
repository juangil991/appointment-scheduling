package com.medisalud.appointmentscheduling.infraestructure.exception;

import com.medisalud.appointmentscheduling.application.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.infraestructure.controller.DoctorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = DoctorController.class)
public class DoctorExceptions {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DoctorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        DoctorResponse response = new DoctorResponse(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
