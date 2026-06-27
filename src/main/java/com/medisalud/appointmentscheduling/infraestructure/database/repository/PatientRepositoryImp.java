package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.domain.model.Patient;
import com.medisalud.appointmentscheduling.domain.repository.PatientRepository;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PatientEntity;
import com.medisalud.appointmentscheduling.infraestructure.mapper.PatientPersistenceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImp implements PatientRepository {
    private final PatientEntityRepository repository;
    private final PatientPersistenceMapper mapper;

    public PatientRepositoryImp(PatientEntityRepository repository, PatientPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Patient save(Patient patient) {
        PatientEntity patientEntity = mapper.toEntity(patient);
        PatientEntity savedEntity = repository.save(patientEntity);
        return mapper.toDomain(savedEntity);
    }
}
