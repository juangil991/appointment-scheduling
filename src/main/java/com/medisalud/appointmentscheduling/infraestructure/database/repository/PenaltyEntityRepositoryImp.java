package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.domain.model.Penalty;
import com.medisalud.appointmentscheduling.domain.repository.PenaltyRepository;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PatientEntity;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PenaltyEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;

@Repository
public class PenaltyEntityRepositoryImp implements PenaltyRepository {

    private final PenaltyEntityRepository penaltyEntityRepository;
    private final PatientEntityRepository patientEntityRepository;

    public PenaltyEntityRepositoryImp(PenaltyEntityRepository penaltyEntityRepository, PatientEntityRepository patientEntityRepository) {
        this.penaltyEntityRepository = penaltyEntityRepository;
        this.patientEntityRepository = patientEntityRepository;
    }

    @Override
    public Penalty save(Penalty penalty) {
        PatientEntity patient = patientEntityRepository.getReferenceById(penalty.patientId());
        PenaltyEntity penaltyEntity = new PenaltyEntity(patient, penalty.createdAt());
        PenaltyEntity createdPenaltyEntity = penaltyEntityRepository.save(penaltyEntity);
        return new Penalty(createdPenaltyEntity.getId(), createdPenaltyEntity.getPatient().getId(), createdPenaltyEntity.getCreatedAt());
    }

    @Override
    public List<Penalty> findByPatientAndCreatedAtBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate) {
        return penaltyEntityRepository.findByPatientIdAndCreatedAtBetween(id, startDate, endDate).stream()
                .map(penaltyEntity -> new Penalty(penaltyEntity.getId(), penaltyEntity.getPatient().getId(), penaltyEntity.getCreatedAt()))
                .toList();
    }
}
