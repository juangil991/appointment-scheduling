package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.infraestructure.database.entity.PenaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PenaltyEntityRepository extends JpaRepository<PenaltyEntity, UUID> {

    List<PenaltyEntity> findByPatientIdAndCreatedAtBetween(UUID patientId, java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
}
