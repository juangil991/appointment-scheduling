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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class AppointmentIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("RF-03: Debe permitir registrar una cita correctamente")
    void registerAppointmentSuccess() throws Exception {
        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("123456789", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b","2026-07-01T10:30:00"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2026-07-01T10:30:00"))
                .andExpect(jsonPath("$.Estado").value("PROGRAMADA"));
    }

    @Test
    @DisplayName("RF-04: Debe permitir cancelar una cita correctamente")
    void cancelAppointmentSuccess() throws Exception {
        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest("b6534bf6-051c-434e-aea5-bd20342bafa0"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2026-06-29T08:00:00"))
                .andExpect(jsonPath("$.Estado").value("CANCELADA"));
    }

    @Test
    @DisplayName("RF-04: Debe lanzar excepcion si no existe el id de la cita")
    void shouldReturnReturnBadRequestWhenIdIsNull() throws Exception {
        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_DATE_REQUIRED));
    }




}
