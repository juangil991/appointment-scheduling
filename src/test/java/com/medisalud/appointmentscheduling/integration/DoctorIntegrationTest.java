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
                .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dra. María González", "Cardiología","555-1001", "maria.gonzalez@medisalud.com"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Nombre").value("Dra. María González"))
                .andExpect(jsonPath("$.Especialidad").value("Cardiología"))
                .andExpect(jsonPath("$.Teléfono").value("555-1001"))
                .andExpect(jsonPath("$.Email").value("maria.gonzalez@medisalud.com"));
    }

    @Test
    @DisplayName("RF-01: Debe lanzar excepción si la especialidad no existe")
    void registerDoctorShouldThrowExceptionForInvalidSpecialty() throws Exception {
        mvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RegisterDoctorTest("Dr. Juan Pérez", "EspecialidadInvalida", "555-1002", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("La especialidad EspecialidadInvalida no es válida. Por favor, elija una especialidad existente.")));

    }
}
