package com.medisalud.appointmentscheduling.infraestructure.database.specification;

import com.medisalud.appointmentscheduling.domain.model.AppointmentFilter;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.AppointmentEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public final class AppointmentSpecification {
    private AppointmentSpecification() {
    }

    public static Specification<AppointmentEntity> byFilter(
            AppointmentFilter filter) {

        Specification<AppointmentEntity> specification = Specification.unrestricted();

        if (filter.doctorId() != null) {
            specification = specification.and(hasDoctor(filter.doctorId()));
        }

        if (filter.patientId() != null) {
            specification = specification.and(hasPatient(filter.patientId()));
        }

        if (filter.status() != null) {
            specification = specification.and(hasStatus(filter.status()));
        }

        if (filter.startDate() != null) {
            specification = specification.and(afterOrEquals(filter.startDate()));
        }

        if (filter.endDate() != null) {
            specification = specification.and(beforeOrEquals(filter.endDate()));
        }

        return specification;
    }

    private static Specification<AppointmentEntity> hasDoctor(UUID doctorId) {

        return (root, query, cb) ->
                cb.equal(root.get("doctor").get("id"), doctorId);
    }

    private static Specification<AppointmentEntity> hasPatient(UUID patientId) {

        return (root, query, cb) ->
                cb.equal(root.get("patient").get("id"), patientId);
    }

    private static Specification<AppointmentEntity> hasStatus(String status) {

        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    private static Specification<AppointmentEntity> afterOrEquals(
            LocalDateTime date) {

        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("appointmentDate"), date);
    }

    private static Specification<AppointmentEntity> beforeOrEquals(
            LocalDateTime date) {

        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("appointmentDate"), date);
    }
}
