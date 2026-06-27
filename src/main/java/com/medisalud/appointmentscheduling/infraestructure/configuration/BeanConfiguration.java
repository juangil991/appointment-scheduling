package com.medisalud.appointmentscheduling.infraestructure.configuration;

import com.medisalud.appointmentscheduling.domain.repository.DoctorRepository;
import com.medisalud.appointmentscheduling.domain.service.DoctorService;
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
}
