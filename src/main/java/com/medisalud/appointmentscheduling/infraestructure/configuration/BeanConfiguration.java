package com.medisalud.appointmentscheduling.infraestructure.configuration;

import com.medisalud.appointmentscheduling.domain.repository.AppointmentRepository;
import com.medisalud.appointmentscheduling.domain.repository.DoctorRepository;
import com.medisalud.appointmentscheduling.domain.repository.PatientRepository;
import com.medisalud.appointmentscheduling.domain.service.AppointmentService;
import com.medisalud.appointmentscheduling.domain.service.DoctorService;
import com.medisalud.appointmentscheduling.domain.service.PatientService;
import com.medisalud.appointmentscheduling.domain.service.SchedulingService;
import com.medisalud.appointmentscheduling.domain.validator.ValidateDoctor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ValidateDoctor validateDoctor(){
        return new ValidateDoctor();
    }

    @Bean
    public DoctorService doctorService(DoctorRepository doctorRepository, ValidateDoctor validateDoctor){
        return new DoctorService(doctorRepository, validateDoctor);
    }

    @Bean
    public PatientService patientService(PatientRepository patientRepository){
        return new PatientService(patientRepository);
    }

    @Bean
    public AppointmentService appointmentService(AppointmentRepository appointmentRepository){
        return new AppointmentService(appointmentRepository);
    }

    @Bean
    public SchedulingService schedulingService(AppointmentRepository appointmentRepository){
        return new SchedulingService(appointmentRepository);
    }
}
