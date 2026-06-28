package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.infraestructure.database.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByDoctorIdAndAppointmentDateBetween(
            UUID doctorId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}
