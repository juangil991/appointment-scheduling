package com.medisalud.appointmentscheduling.application.usecase;

import com.medisalud.appointmentscheduling.application.dto.AppointmentRequest;
import com.medisalud.appointmentscheduling.application.dto.AppointmentResponse;
import com.medisalud.appointmentscheduling.application.mapper.AppointmentMapper;
import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.model.Patient;
import com.medisalud.appointmentscheduling.domain.service.AppointmentService;
import com.medisalud.appointmentscheduling.domain.service.DoctorService;
import com.medisalud.appointmentscheduling.domain.service.PatientService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReserveAppointmentUseCase {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentMapper mapper;

    public ReserveAppointmentUseCase(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.mapper = mapper;
    }

    public AppointmentResponse reserveAppointment(AppointmentRequest request) {
        Doctor doctor = doctorService.getDoctorById(request.doctorId());
        Patient patient = patientService.getPatientByIdentificationNumber(request.patientIdentification());
        LocalDateTime appointmentDateTime = LocalDateTime.parse(request.appointmentDate());
        Appointment appointment = new Appointment(null, patient, doctor, appointmentDateTime, "PROGRAMADA");
        return mapper.toResponse(appointmentService.scheduleAppointment(appointment));
    }
}
