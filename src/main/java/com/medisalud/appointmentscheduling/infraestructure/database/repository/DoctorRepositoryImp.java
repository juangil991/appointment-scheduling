package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.repository.DoctorRepository;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.DoctorEntity;
import com.medisalud.appointmentscheduling.infraestructure.mapper.DoctorPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DoctorRepositoryImp implements DoctorRepository {

    private final DoctorEntityRepository jpaRepository;
    private final DoctorPersistenceMapper mapper;

    public DoctorRepositoryImp(DoctorEntityRepository jpaRepository, DoctorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Doctor save(Doctor doctor) {
        DoctorEntity doctorEntity = mapper.toEntity(doctor);
        return mapper.toDomain(jpaRepository.save(doctorEntity));
    }

    @Override
    public Optional<Doctor> findById(UUID id) {
        Optional<DoctorEntity> doctorEntity = jpaRepository.findById(id);
        return doctorEntity.map(mapper::toDomain);
    }
}
