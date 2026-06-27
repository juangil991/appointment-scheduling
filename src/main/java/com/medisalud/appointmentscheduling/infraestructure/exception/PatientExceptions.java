package com.medisalud.appointmentscheduling.infraestructure.exception;


import com.medisalud.appointmentscheduling.application.dto.DoctorResponse;
import com.medisalud.appointmentscheduling.application.dto.PatientResponse;
import com.medisalud.appointmentscheduling.infraestructure.controller.PatientController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = PatientController.class)
public class PatientExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PatientResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        PatientResponse response = new PatientResponse(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
