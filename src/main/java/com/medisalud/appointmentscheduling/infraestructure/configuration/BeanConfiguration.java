package com.medisalud.appointmentscheduling.infraestructure.configuration;

import com.medisalud.appointmentscheduling.domain.validator.ValidateDoctor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ValidateDoctor validateDoctor(){
        return new ValidateDoctor();
    }
}
