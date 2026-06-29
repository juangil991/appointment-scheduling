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
    public static final String DOCTOR_NOT_FOUND = "El doctor no fue encontrado. Por favor, verifique el ID proporcionado.";
    public static final String APPOINTMENT_PENALTY = "No es posible agendar una nueva cita porque el paciente tiene 3 o más penalizaciones registradas en los últimos 30 días. Inténtelo nuevamente cuando haya finalizado el período de restricción.";
    public static final String START_DATE_GREATER_THAN_END_DATE= "El rango de fechas es inválido. La fecha de inicio debe ser anterior o igual a la fecha de fin.";
    public static final String APPOINTMENT_DATE_REQUIRED= "El identificador de la cita es obligatorio.";
    public static final String APPOINTMENT_NOT_FOUND= "La cita no fue encontrada. Por favor, verifique el ID proporcionado.";
    public static final String APPOINTMENT_ALREADY_CANCELED = "La cita ya se encuentra cancelada.";
    public static final String SPECIALTY_REQUIRED = "La especialidad es obligatoria";
    public static final String PHONE_NUMBER_INVALID = "El teléfono debe contener exactamente 7 o 10 dígitos.";
    public static final String PATIENT_ID_REQUIRED = "El número de identificación del paciente es obligatorio";
    public static final String DOCTOR_ID_REQUIRED = "El número de identificación del medico es obligatorio";
    public static final String APPOINTMENT_DATETIME_REQUIRED = "La fecha de la cita es obligatorio";
    public static final String APPOINTMENT_ID_REQUIRED = "El identificador de la cita es obligatorio";
    public static final String RESCHEDULE_DATE_REQUIRED = "La nueva fecha de la cita es obligatoria";
    public static final String START_DATE_REQUIRED = "La fecha de inicio es obligatoria";
    public static final String END_DATE_REQUIRED = "La fecha de fin es obligatoria";
    public static final String NAME_REQUIRED = "El nombre es obligatorio";
    public static final String NAME_INVALID = "El nombre debe tener entre 3 y 100 caracteres";
    public static final String NAME_FORMAT_INVALID = "Nombre inválido";
    public static final String EMAIL_INVALID = "Formato de email invalido";
    public static final String DOCUMENT_NUMBER_REQUIRED = "El documento de identidad es obligatorio.";
    public static final String DOCUMENT_NUMBER_INVALID = "El documento de identidad debe tener al menos 7 dígitos.";
    public static final String PHONE_NUMBER_REQUIRED= "El teléfono es obligatorio.";
    public static final String PATIENT_PHONE_NUMBER_INVALID="El número de teléfono debe de tener minimo 7 digitos";
    public static final String EMAIL_REQUIRED = "El email es obligatorio.";

}
