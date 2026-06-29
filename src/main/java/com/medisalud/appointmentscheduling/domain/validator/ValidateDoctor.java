package com.medisalud.appointmentscheduling.domain.validator;

import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import com.medisalud.appointmentscheduling.domain.model.Doctor;
import com.medisalud.appointmentscheduling.domain.model.Specialty;

import java.util.Arrays;
import java.util.Optional;

public class ValidateDoctor {

    public void validateDoctor(Doctor doctor){
        validateSpecialty(doctor.specialty());
    }

    protected void validateSpecialty(String specialty){
        Optional<Specialty> doctorSpecialty = Arrays.stream(Specialty.values())
                .filter(specialtyCategory -> specialtyCategory.getDescription().equalsIgnoreCase(specialty) ||
                        specialtyCategory.name().equalsIgnoreCase(specialty))
                .findFirst();
        if(doctorSpecialty.isEmpty()){
            throw new IllegalArgumentException(String.format(ErrorMessages.SPECIALTY_NOT_FOUND, specialty));
        }

    }

}
