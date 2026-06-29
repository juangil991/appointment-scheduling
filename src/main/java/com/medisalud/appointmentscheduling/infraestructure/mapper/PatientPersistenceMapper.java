package com.medisalud.appointmentscheduling.infraestructure.mapper;

import com.medisalud.appointmentscheduling.domain.model.Patient;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PatientEntity;
import org.springframework.stereotype.Component;

@Component
public class PatientPersistenceMapper {
    public PatientEntity toEntity(Patient patient) {

        return new PatientEntity(
                patient.name(),
                patient.identificationNumber(),
                patient.phoneNumber(),
                patient.email(),
                patient.birthDay());
    }

    public Patient toDomain(PatientEntity entity) {

        return new Patient(
                entity.getId(),
                entity.getName(),
                entity.getIdentificationNumber(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getBirthDay());
    }
}
