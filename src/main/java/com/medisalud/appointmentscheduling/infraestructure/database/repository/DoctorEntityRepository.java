package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.infraestructure.database.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorEntityRepository extends JpaRepository<DoctorEntity, UUID>  {

    @Override
    Optional<DoctorEntity> findById(UUID uuid);
}
