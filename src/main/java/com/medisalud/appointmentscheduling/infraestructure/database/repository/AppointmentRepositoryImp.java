package com.medisalud.appointmentscheduling.infraestructure.database.repository;

import com.medisalud.appointmentscheduling.domain.model.Appointment;
import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.AppointmentEntity;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.DoctorEntity;
import com.medisalud.appointmentscheduling.infraestructure.database.entity.PatientEntity;
import com.medisalud.appointmentscheduling.infraestructure.mapper.AppointmentPersistenceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class AppointmentRepositoryImp  implements AppointmentRepository {

    private final AppointmentEntityRepository appointmentRepository;
    private final DoctorEntityRepository doctorRepository;
    private final PatientEntityRepository patientRepository;
    private final AppointmentPersistenceMapper mapper;

    public AppointmentRepositoryImp(AppointmentEntityRepository appointmentRepository, DoctorEntityRepository doctorRepository, PatientEntityRepository patientRepository, AppointmentPersistenceMapper mapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Override
    public Appointment save(Appointment appointment) {
        DoctorEntity doctorEntity = doctorRepository.getReferenceById(appointment.doctor().id());
        PatientEntity patient = patientRepository.getReferenceById(appointment.patient().id());
        AppointmentEntity appointmentEntity = mapper.toEntity(appointment,doctorEntity,patient);
        return mapper.toDomain(appointmentRepository.save(appointmentEntity));
    }


    @Override
    public List<Appointment> findByDoctorAndDateRange(UUID doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<AppointmentEntity> appointmentEntity = appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId,startDate,endDate);
        return appointmentEntity.stream().map(mapper::toDomain).toList();
    }
}
