package com.medisalud.appointmentscheduling.infraestructure.mapper;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.AppointmentEntity;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.DoctorEntity;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class AppointmentPersistenceMapper {

    private final DoctorPersistenceMapper doctorMapper;
    private final PatientPersistenceMapper patientMapper;

    public AppointmentPersistenceMapper(DoctorPersistenceMapper doctorMapper, PatientPersistenceMapper patientMapper) {
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    public AppointmentEntity toEntity(Appointment appointment, DoctorEntity doctor, PatientEntity patient) {

        return new AppointmentEntity(
                doctor,
                patient,
                appointment.appointmentDate(),
                appointment.status(),
                null);
    }

    public Appointment toDomain(AppointmentEntity entity) {
        if(entity ==null){return null;}
        return new Appointment(
                entity.getId(),
                patientMapper.toDomain(entity.getPatient()),
                doctorMapper.toDomain(entity.getDoctor()),
                entity.getAppointmentDate(),
                entity.getStatus(),
                entity.getCancellationDate());
    }

}
