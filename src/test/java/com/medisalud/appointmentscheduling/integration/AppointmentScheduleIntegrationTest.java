package com.medisalud.appointmentscheduling.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medisalud.appointmentscheduling.application.dto.AppointmentScheduleResponse;
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


import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class AppointmentScheduleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final UUID doctorId = UUID.fromString("770b3ce1-ad10-4047-af7c-a8a7ad8aa49b");

    @Test
    @DisplayName("RF-04: Debe permitir consultar una citas disponibles correctamente")
    void shouldReturnAvailableAppointmentSchedule() throws Exception {

        mockMvc.perform(get("/api/v1/appointments/doctor/{doctorId}", doctorId)
                        .param("startDate", "2026-06-26T00:00:00")
                        .param("endDate", "2026-06-28T23:59:59")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].date").value("2026-06-26"))
                .andExpect(jsonPath("$[0].timeSlots[0]").value("08:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[1]").value("08:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[2]").value("09:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[3]").value("09:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[4]").value("10:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[5]").value("10:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[6]").value("11:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[7]").value("11:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[8]").value("12:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[9]").value("12:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[10]").value("13:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[11]").value("13:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[12]").value("14:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[13]").value("14:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[14]").value("15:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[15]").value("15:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[16]").value("16:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[17]").value("16:30:00"))
                .andExpect(jsonPath("$[0].timeSlots[18]").value("17:00:00"))
                .andExpect(jsonPath("$[0].timeSlots[19]").value("17:30:00"))


                .andExpect(jsonPath("$[1].date").value("2026-06-27"))
                .andExpect(jsonPath("$[1].timeSlots[0]").value("08:00:00"))
                .andExpect(jsonPath("$[1].timeSlots[1]").value("08:30:00"))
                .andExpect(jsonPath("$[1].timeSlots[2]").value("09:00:00"))
                .andExpect(jsonPath("$[1].timeSlots[3]").value("09:30:00"))
                .andExpect(jsonPath("$[1].timeSlots[4]").value("10:00:00"))
                .andExpect(jsonPath("$[1].timeSlots[5]").value("10:30:00"))
                .andExpect(jsonPath("$[1].timeSlots[6]").value("11:00:00"))
                .andExpect(jsonPath("$[1].timeSlots[7]").value("11:30:00"))
                .andExpect(jsonPath("$[1].timeSlots[8]").value("12:00:00"))

                .andExpect(jsonPath("$[2].date").value("2026-06-28"))
                .andExpect(jsonPath("$[2].timeSlots").isEmpty());
    }

    @Test
    @DisplayName("RF-04: Debe lanzar excepcion cuando la fecha de inicio es mayor a la fecha de fin")
    void shouldReturnBadRequestWhenStartDateIsGreaterThanEndDate() throws Exception {
        mockMvc.perform(get("/api/v1/appointments/doctor/{doctorId}", doctorId)
                        .param("startDate", "2027-06-26T00:00:00")
                        .param("endDate", "2026-06-28T23:59:59"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Required request parameter 'startDate' for method parameter type LocalDateTime is not present")));
    }

    @Test
    @DisplayName("RF-04: Debe lanzar excepcion cuando no se envien los parametros de fecha de inicio")
    void shouldReturnBadRequestWhenStartDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/v1/appointments/doctor/{doctorId}", doctorId)
                        .param("endDate", "2026-06-30T23:59:59"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Required request parameter 'startDate' for method parameter type LocalDateTime is not present")));
    }

    @Test
    @DisplayName("RF-04: Debe lanzar excepcion cuando no se envien los parametros de fecha de fin")
    void shouldReturnBadRequestWhenEndDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/v1/appointments/doctor/{doctorId}", doctorId)
                        .param("startDate", "2026-06-29T00:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Mensaje", is("Required request parameter 'endDate' for method parameter type LocalDateTime is not present")));
    }

    @Test
    @DisplayName("RF-04: Debe lanzar excepcion cuando el medico no esta registrado")
    void shouldReturnBadRequestWhenDoctorNotExist() throws Exception {

        mockMvc.perform(get("/api/v1/appointments/doctor/{doctorId}", "3929b3b7-e4a2-4fbc-b791-29945b49ba74")
                        .param("startDate", "2026-06-29T00:00:00")
                        .param("endDate", "2026-06-30T23:59:59"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.Mensaje", is(ErrorMessages.DOCTOR_NOT_FOUND)));
    }

}
