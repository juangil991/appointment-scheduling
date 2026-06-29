package com.medisalud.appointmentscheduling.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisalud.appointmentscheduling.domain.constants.ErrorMessages;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class DoctorIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("RF-01: Debe permitir registrar un doctor correctamente")
    void registerDoctorSuccess() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dra. María González", "Cardiología","5551001", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Nombre").value("Dra. María González"))
                .andExpect(jsonPath("$.Especialidad").value("Cardiología"))
                .andExpect(jsonPath("$.Teléfono").value("5551001"))
                .andExpect(jsonPath("$.Email").value("maria.gonzalez@medisalud.com"));
    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si la especialidad no existe")
    void registerDoctorShouldThrowExceptionForInvalidSpecialty() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dr. Juan Pérez", "EspecialidadInvalida", "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("La especialidad EspecialidadInvalida no es válida. Por favor, elija una especialidad existente.")));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si el nombre es null")
    void registerDoctorShouldThrowExceptionForNullName() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest(null, "EspecialidadInvalida", "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is(ErrorMessages.NAME_REQUIRED)));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si el nombre contiene menos de 3 caracteres")
    void registerDoctorShouldThrowExceptionForShortName() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("ca", "Cardiología", "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El nombre debe tener entre 3 y 100 caracteres")));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si el nombre contiene mas de 100 caracteres")
    void registerDoctorShouldThrowExceptionForLongName() throws Exception {
        String name = "Este es un nombre de prueba extremadamente largo diseñado para superar el límite máximo de cien caracteres permitido.";
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest(name, "Cardiología", "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El nombre debe tener entre 3 y 100 caracteres")));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si es un nombre invalido")
    void registerDoctorShouldThrowExceptionForInvalidName() throws Exception {
        String name = "sdaf21121asd--asdf";
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest(name, "Cardiología", "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Nombre inválido")));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si la especialidad es null")
    void registerDoctorShouldThrowExceptionForNullSpecialty() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dr. Juan Pérez", null, "5551002", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is(ErrorMessages.SPECIALTY_REQUIRED)));

    }

    @Test
    @DisplayName("RF-01: Debe permitir registrar un doctor correctamente sin telefono")
    void registerDoctorSuccessWithoutPhone() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dra. María González", "Cardiología",null, "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Nombre").value("Dra. María González"))
                .andExpect(jsonPath("$.Especialidad").value("Cardiología"))
                .andExpect(jsonPath("$.Teléfono").doesNotExist())
                .andExpect(jsonPath("$.Email").value("maria.gonzalez@medisalud.com"));

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si el telefono contiene un valor diferente a 7 o 10 digitos")
    void registerDoctorShouldThrowExceptionForInvalidPhoneNumber() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dr. Juan Pérez", "Cardiología", "55510020", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is(ErrorMessages.PHONE_NUMBER_INVALID)));

    }

    @Test
    @DisplayName("RF-01: Debe permitir registrar un doctor correctamente sin email")
    void registerDoctorSuccessWithoutEmail() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dra. María González", "Cardiología","5551002", null))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Nombre").value("Dra. María González"))
                .andExpect(jsonPath("$.Especialidad").value("Cardiología"))
                .andExpect(jsonPath("$.Teléfono").value("5551002"))
                .andExpect(jsonPath("$.Email").doesNotExist());

    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si el email tiene un formato invalido")
    void registerDoctorShouldThrowExceptionForInvalidEmail() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dr. Juan Pérez", "Cardiología", "5551002", "maria.gonzalezmedisalud.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Formato de email invalido")));

    }


}
