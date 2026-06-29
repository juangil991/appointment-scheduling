package com.medisalud.appointmentscheduling.domain.constants;

public final class ErrorMessages {

    private ErrorMessages() {
    }

    public static final String SPECIALTY_NOT_FOUND = "La especialidad %s no es válida. Por favor, elija una especialidad existente.";
    public static final String APPOINTMENT_DATE_INVALID = "La fecha o la hora seleccionadas no corresponden a un horario de atención válido. Las citas solo pueden programarse de lunes a viernes entre las 08:00 y las 18:00, los sábados entre las 08:00 y las 13:00, en intervalos de 30 minutos. No se atiende los domingos ni los días festivos.";
    public static final String APPOINTMENT_DUPLICATED= "El médico ya tiene una cita programada en la fecha y hora seleccionadas. Por favor, elija otro horario.";
    public static final String PATIENT_APPOINTMENT_DUPLICATED= "El paciente ya tiene una cita programada en la fecha y hora seleccionadas. Por favor, elija otro horario.";
    public static final String APPOINTMENT_DATE_PAST = "La cita no puede programarse en una fecha y hora anteriores a la fecha y hora actuales.";
    public static final String FUTURE_BIRTH_DAY = "No es posible agendar la cita porque la fecha de nacimiento del paciente es posterior a la fecha actual.";
}
