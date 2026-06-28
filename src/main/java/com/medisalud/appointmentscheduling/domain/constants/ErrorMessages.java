package com.medisalud.appointmentscheduling.domain.constants;

public final class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String SPECIALTY_NOT_FOUND = "La especialidad %s no es válida. Por favor, elija una especialidad existente.";
    public static final String APPOINTMENT_DATE_INVALID = "La fecha o la hora seleccionadas no corresponden a un horario de atención válido. Las citas solo pueden programarse de lunes a viernes entre las 08:00 y las 18:00, los sábados entre las 08:00 y las 13:00, en intervalos de 30 minutos. No se atiende los domingos ni los días festivos.";

}
