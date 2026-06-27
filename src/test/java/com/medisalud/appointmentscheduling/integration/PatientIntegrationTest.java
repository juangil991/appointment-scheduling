package com.medisalud.appointmentscheduling.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PatientIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("RF-02: Debe permitir registrar un paciente correctamente")
    void registerPatientSuccess() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "1152115211","5551001", "lu.valencia@email.com"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Nombre").value("Louise"))
                .andExpect(jsonPath("$.Documento de identidad").value("1152115211"))
                .andExpect(jsonPath("$.Teléfono").value("5551001"))
                .andExpect(jsonPath("$.Email").value("lu.valencia@email.com"));
    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si la nombre es null")
    void registerDoctorShouldThrowExceptionForNullName() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest(null, "1152115211","5551001", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El nombre es obligatorio.")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el nombre tienen menos de 3 caracteres")
    void registerDoctorShouldThrowExceptionForShortName() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("lu", "1152115211","5551001", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El nombre debe tener entre 3 y 100 caracteres")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el nombre tienen mas de 100 caracteres")
    void registerDoctorShouldThrowExceptionForLongName() throws Exception {
        String name = "Este es un nombre de prueba extremadamente largo diseñado para superar el límite máximo de cien caracteres permitido.";
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest(name, "1152115211","5551001", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El nombre debe tener entre 3 y 100 caracteres")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el documento es null")
    void registerDoctorShouldThrowExceptionForNullIdentificationNumber() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", null,"5551001", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El documento de identidad es obligatorio.")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el documento tiene menos de 7 caracteres")
    void registerDoctorShouldThrowExceptionForShortIdentificationName() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "123456","5551001", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El documento de identidad debe de tener minimo 7 digitos")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el telefono es null")
    void registerDoctorShouldThrowExceptionForNullPhoneNumber() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "12345678",null, "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El teléfono es obligatorio.")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el telefono tiene menos de 7 digitos")
    void registerDoctorShouldThrowExceptionForShortPhoneNumber() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "12345678","123", "lu.valencia@email.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El teléfono debe tener minimo 7 dígitos.")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el email es null")
    void registerDoctorShouldThrowExceptionForNullEmail() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "12345678","12345678", null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("El email es obligatorio.")));

    }

    @Test
    @DisplayName("RF-02: Debe lanzar excepción si el email no tienen el formato correcto")
    void registerDoctorShouldThrowExceptionForInvalidEmail() throws Exception {
        mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterPatientTest("Louise", "12345678","12345678", "lu.valenciagmail.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Formato de email invalido")));

    }



}
