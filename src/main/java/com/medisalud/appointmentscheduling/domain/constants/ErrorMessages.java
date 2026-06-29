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
    public static final String PATIENT_NOT_FOUND = "El paciente con ID %s no fue encontrado. Por favor, verifique el ID proporcionado.";
    public static final String DOCTOR_NOT_FOUND = "El doctor no fue encontrado. Por favor, verifique el ID proporcionado.";
    public static final String APPOINTMENT_PENALTY = "No es posible agendar una nueva cita porque el paciente tiene 3 o más penalizaciones registradas en los últimos 30 días. Inténtelo nuevamente cuando haya finalizado el período de restricción.";
    public static final String START_DATE_GREATER_THAN_END_DATE= "El rango de fechas es inválido. La fecha de inicio debe ser anterior o igual a la fecha de fin.";
}
