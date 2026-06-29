package com.medisalud.appointmentscheduling.infraestructure.exception;

import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.domain.exception.AppointmentConflictException;
import com.medisalud.appointmentscheduling.domain.exception.AppointmentNotFoundException;
import com.medisalud.appointmentscheduling.domain.exception.InvalidAppointmentScheduleException;
import com.medisalud.appointmentscheduling.infraestructure.controller.AppointmentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<AppointmentResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        AppointmentResponse response = new AppointmentResponse(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<AppointmentResponse> handleAppointmentNotFoundException(AppointmentNotFoundException exception) {
        AppointmentResponse response = new AppointmentResponse(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppointmentResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        AppointmentResponse response = new AppointmentResponse(exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<AppointmentResponse> handleAppointmentConflictException(AppointmentConflictException exception) {
        AppointmentResponse response = new AppointmentResponse(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}
