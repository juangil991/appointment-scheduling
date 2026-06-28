package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.infraestructure.database.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, UUID> {
}
