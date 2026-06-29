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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @DisplayName("RN-01: Debe lanzar error si la fechas esta fuera del horario de atencion")
    void shouldReturnReturnBadRequestWhenScheduleOutDateRange() throws Exception {
        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("123456789", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b","2026-07-01T18:30:00"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_DATE_INVALID));
    }

    @Test
    @DisplayName("RN-02: Debe lanzar excepcion cuando se intenta reservar una cita en un horario ya ocupado de un medico")
    void shouldReturnReturnBadRequestWhenDuplicatedAppointment() throws Exception {
        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("123456789", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b","2060-06-29T08:30:00"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2060-06-29T08:30:00"))
                .andExpect(jsonPath("$.Estado").value("PROGRAMADA"));


        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("987654321", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b","2060-06-29T08:30:00"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_DUPLICATED));
    }

    @Test
    @DisplayName("RN-04: Debe lanzar excepcion cuando se intenta reservar una cita en un horario ya ocupado de un paciente")
    void shouldReturnReturnBadRequestWhenDuplicatedPatientAppointment() throws Exception {
        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("123456789", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b","2060-06-29T08:30:00"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2060-06-29T08:30:00"))
                .andExpect(jsonPath("$.Estado").value("PROGRAMADA"));

        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("123456789", "d1259a02-575a-43aa-a9ad-c7d6d760a454","2060-06-29T08:30:00"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.PATIENT_APPOINTMENT_DUPLICATED));
    }

    @Test
    @DisplayName("RN-03: Debe lanzar excepcion cuando se intenta reservar una cita con un paciente con fecha de nacimiento futura")
    void shouldReturnReturnBadRequestWhenFutureBirthDay() throws Exception {

        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("115215512254", "d1259a02-575a-43aa-a9ad-c7d6d760a454","2060-06-29T08:30:00"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.FUTURE_BIRTH_DAY));
    }

    @Test
    @DisplayName("RN-05: Debe lanzar excepcion cuando se intenta reservar una cita con un paciente penalizado")
    void shouldReturnReturnBadRequestWhenPenaltyPatient() throws Exception {

        mvc.perform(post("/api/v1/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ReserveAppointmentTest("1152228115", "d1259a02-575a-43aa-a9ad-c7d6d760a454","2060-06-29T08:30:00"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_PENALTY));
    }

    @Test
    @DisplayName("RF-05: Debe permitir cancelar una cita correctamente")
    void cancelAppointmentSuccess() throws Exception {
        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest("b6534bf6-051c-434e-aea5-bd20342bafa0"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2026-06-29T08:30:00"))
                .andExpect(jsonPath("$.Fecha").exists())
                .andExpect(jsonPath("$.Estado").value("CANCELADA"));
    }

    @Test
    @DisplayName("RF-05: Debe permitir reagendar una cita correctamente")
    void reScheduleAppointmentSuccess() throws Exception {
        mvc.perform(patch("/api/v1/appointments/reschedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RescheduleAppointmentTest("b6534bf6-051c-434e-aea5-bd20342bafa0","2026-07-01T17:00:00"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2026-07-01T17:00:00"))
                .andExpect(jsonPath("$.Fecha").exists())
                .andExpect(jsonPath("$.Estado").value("PROGRAMADA"));
    }

    @Test
    @DisplayName("RF-05: Debe lanzar excepcion al cancelar una cita que ya esta cancelada")
    void shouldReturnReturnConflictWhenAppointmentAlreadyCanceled() throws Exception {
        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest("b6534bf6-051c-434e-aea5-bd20342bafa0"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id").exists())
                .andExpect(jsonPath("$.Paciente").value("Louise"))
                .andExpect(jsonPath("$.Médico").value("Dra María Gonzáles"))
                .andExpect(jsonPath("$.Fecha").value("2026-06-29T08:30:00"))
                .andExpect(jsonPath("$.Fecha").exists())
                .andExpect(jsonPath("$.Estado").value("CANCELADA"));

        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest("b6534bf6-051c-434e-aea5-bd20342bafa0"))))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_ALREADY_CANCELED));
    }

    @Test
    @DisplayName("RF-05: Debe lanzar excepcion si no existe el id de la cita")
    void shouldReturnReturnBadRequestWhenIdIsNull() throws Exception {
        mvc.perform(patch("/api/v1/appointments/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CancelAppointmentTest(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje").value(ErrorMessages.APPOINTMENT_DATE_REQUIRED));
    }

    @Test
    void shouldReturnAllAppointments() throws Exception {

        mvc.perform(get("/api/v1/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));

    }

    @Test
    void shouldFilterByDoctor() throws Exception {

        mvc.perform(get("/api/v1/appointments")
                        .param("doctorId", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void shouldFilterByPatient() throws Exception {

        mvc.perform(get("/api/v1/appointments")
                        .param("patientId", "efdb5324-54e5-4b52-a574-5db128e571c9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));

    }

    @Test
    void shouldFilterByStatus() throws Exception {

        mvc.perform(get("/api/v1/appointments")
                        .param("status", "PROGRAMADA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void shouldFilterByDateRange() throws Exception {

        mvc.perform(get("/api/v1/appointments")
                        .param("startDate", "2026-06-29T00:00:00")
                        .param("endDate", "2026-06-29T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }


    @Test
    void shouldFilterByAllParameters() throws Exception {

        mvc.perform(get("/api/v1/appointments")
                        .param("doctorId", "770b3ce1-ad10-4047-af7c-a8a7ad8aa49b")
                        .param("patientId", "efdb5324-54e5-4b52-a574-5db128e571c9")
                        .param("status", "PROGRAMADA")
                        .param("startDate", "2026-06-29T00:00:00")
                        .param("endDate", "2026-06-29T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

    }




}
