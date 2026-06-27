package com.medisalud.appointmentscheduling.infraestructure.mapper;

import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.DoctorEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorPersistenceMapper {

    public DoctorEntity toEntity(Doctor doctor) {

        return new DoctorEntity(
                doctor.name(),
                doctor.specialty(),
                doctor.phoneNumber(),
                doctor.email());
    }

    public Doctor toDomain(DoctorEntity entity) {

        return new Doctor(
                entity.getId(),
                entity.getName(),
                entity.getSpecialty(),
                entity.getPhone(),
                entity.getEmail());
    }
}
