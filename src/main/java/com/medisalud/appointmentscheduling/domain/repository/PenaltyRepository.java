package com.medisalud.appointmentscheduling.domain.repository;

import com.medisalud.appointmentscheduling.domain.model.Penalty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PenaltyRepository {

    Penalty save(Penalty penalty);

    List<Penalty> findByPatientAndCreatedAtBetween(UUID id, LocalDateTime startDate, LocalDateTime endDate);
}
