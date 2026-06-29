# Sistema de Agendamiento de Citas Médicas

Backend desarrollado como solución para la prueba técnica de **MediSalud**, cuyo objetivo es permitir la gestión de médicos, pacientes y citas médicas mediante una API REST.

---

# Tecnologías utilizadas

| Tecnología | Versión | Propósito |
|------------|----------|-----------|
| Java | 25 | Lenguaje de programación |
| Spring Boot | 3.5.16 | Framework principal para la construcción de la API REST |
| Gradle | 8.x | Automatización de compilación y gestión de dependencias |
| Spring Data JPA | Incluido en Spring Boot 3.5.16 | Persistencia de datos |
| Hibernate ORM | 6.x | Implementación JPA utilizada por Spring Data |
| Spring Validation | Incluido en Spring Boot 3.5.16 | Validación de DTOs y solicitudes HTTP |
| Spring Web | Incluido en Spring Boot 3.5.16 | Desarrollo de servicios REST |
| H2 Database | Runtime | Base de datos en memoria para pruebas y desarrollo |
| JUnit 5 | Incluido en Spring Boot Starter Test | Pruebas unitarias |
| Spring Test (MockMvc) | Incluido en Spring Boot Starter Test | Pruebas de integración de la API |
| GitHub Actions | - | Integración Continua (CI/CD) |

---
# Ejecución local

## Requisitos

- Java 25
- Gradle 8+

## Clonar el proyecto

```bash
git clone https://github.com/juangil991/appointment-scheduling.git
```

## Compilar

```bash
./gradlew clean build
```

## Ejecutar

```bash
./gradlew bootRun
```

La aplicación iniciará en

```
http://localhost:8080
```

---

# Ejecutar pruebas

```bash
./gradlew test
```

Pruebas de integración

```bash
./gradlew integrationTest
```

---

# Consola H2

```
http://localhost:8080/h2-console
```

---

# Arquitectura

El proyecto fue desarrollado siguiendo una **Arquitectura Hexagonal esta decisión fue tomada porque el proyecto está pensado para evolucionar y crecer en el tiempo, permitiendo incorporar nuevas tecnologías sin afectar la lógica de negocio.

---

# Estructura del proyecto

```
src
└── main
    ├── java
    │   └── com.medisalud.appointmentscheduling
    │
    ├── application
    │   ├── dto
    │   │   ├── AppointmentCancelRequest
    │   │   ├── AppointmentRequest
    │   │   ├── AppointmentRescheduleRequest
    │   │   ├── AppointmentResponse
    │   │   ├── AppointmentScheduleRequest
    │   │   ├── AppointmentScheduleResponse
    │   │   ├── DoctorRequest
    │   │   ├── DoctorResponse
    │   │   ├── PatientRequest
    │   │   └── PatientResponse
    │   │
    │   ├── mapper
    │   │   ├── AppointmentMapper
    │   │   ├── DoctorMapper
    │   │   └── PatientMapper
    │   │
    │   └── usecase
    │       ├── CancelAppointmentUseCase
    │       ├── CheckAppointmentScheduleUseCase
    │       ├── ListAppointmentUseCase
    │       ├── RegisterDoctorUseCase
    │       ├── RegisterPatientUseCase
    │       ├── RescheduleAppointmentUseCase
    │       └── ReserveAppointmentUseCase
    │
    ├── domain
    │   ├── constants
    │   │   ├── ErrorMessages
    │   │   └── WorkingHours
    │   │
    │   ├── exception
    │   │   ├── AppointmentConflictException
    │   │   ├── AppointmentNotFoundException
    │   │   ├── BusinessException
    │   │   └── InvalidAppointmentScheduleException
    │   │
    │   ├── model
    │   │   ├── Appointment
    │   │   ├── AppointmentFilter
    │   │   ├── AppointmentSchedule
    │   │   ├── Doctor
    │   │   ├── Patient
    │   │   ├── Penalty
    │   │   └── Specialty
    │   │
    │   ├── repository
    │   │   ├── AppointmentRepository
    │   │   ├── DoctorRepository
    │   │   ├── PatientRepository
    │   │   └── PenaltyRepository
    │   │
    │   ├── service
    │   │   ├── AppointmentService
    │   │   ├── DoctorService
    │   │   ├── ListAppointmentService
    │   │   ├── PatientService
    │   │   └── SchedulingService
    │   │
    │   ├── util
    │   │   └── AppointmentScheduleUtil
    │   │
    │   └── validator
    │       ├── AppointmentValidation
    │       ├── SchedulingValidator
    │       └── ValidateDoctor
    │
    └── infrastructure
        ├── configuration
        │   └── BeanConfiguration
        │
        ├── controller
        │   ├── AppointmentController
        │   ├── DoctorController
        │   └── PatientController
        │
        ├── database
        │   ├── entity
        │   │   ├── AppointmentEntity
        │   │   ├── DoctorEntity
        │   │   ├── PatientEntity
        │   │   └── PenaltyEntity
        │   │
        │   ├── mapper
        │   │   ├── AppointmentPersistenceMapper
        │   │   ├── DoctorPersistenceMapper
        │   │   └── PatientPersistenceMapper
        │   │
        │   ├── repository
        │   │   ├── AppointmentEntityRepository
        │   │   ├── AppointmentRepositoryImpl
        │   │   ├── DoctorEntityRepository
        │   │   ├── DoctorRepositoryImpl
        │   │   ├── PatientEntityRepository
        │   │   ├── PatientRepositoryImpl
        │   │   ├── PenaltyEntityRepository
        │   │   └── PenaltyRepositoryImpl
        │   │
        │   └── specification
        │       └── AppointmentSpecification
        │
        └── exception
            ├── AppointmentExceptionAdvice
            ├── DoctorExceptionAdvice
            └── PatientExceptionAdvice
```

## Organización por capas

### Domain

Es el núcleo de la aplicación y no depende de ninguna tecnología externa.

Contiene:

- Modelos de dominio.
- Interfaces de repositorios (Ports).
- Servicios de dominio.
- Validadores.
- Excepciones de negocio.
- Constantes.
- Utilidades propias del dominio.

Esta capa concentra toda la lógica del negocio y puede reutilizarse independientemente del framework utilizado.

### Application

Orquesta los casos de uso del sistema.

Aquí se encuentran:

- Casos de uso.
- DTOs de entrada y salida.
- Mappers entre DTOs y el dominio.

Los casos de uso coordinan la ejecución de los servicios del dominio sin conocer detalles de infraestructura.

### Infrastructure

Implementa los adaptadores externos necesarios para que la aplicación funcione.

Incluye:

- Controladores REST.
- Implementaciones de repositorios.
- Entidades JPA.
- Mappers de persistencia.
- Specifications para consultas dinámicas.
- Configuración de Spring.
- Manejo centralizado de excepciones.

Esta capa puede modificarse (por ejemplo cambiar H2 por PostgreSQL o exponer una API GraphQL) sin afectar la lógica del negocio.
---

# Documentación de la API

## Endpoints de la API

A continuación, se documentan los endpoints expuestos.

### 1. Gestión de Médicos

#### Registrar un Médico
Registra un nuevo médico en el sistema validando sus datos.

* **URL:** `/api/v1/doctors`
* **Método:** `POST`
* **Body Request (`DoctorRequest`):**
  ```json
     {
          "name": "Dra. Benjamin",
          "specialty": "Cardiología",
          "phoneNumber": "5551001",
          "email": "benjamin.gonzalez@medisalud.com",
          "birthDay": "1985-04-20"
     }
  ```
* **Success Response (201 Created) (`DoctorResponse`):**
  ```json
     {
          "Id": "3cbea3df-6a1d-4013-a8d6-775f6a27601b",
          "Nombre": "Dr. Benjamin",
          "Especialidad": "Cardiología",
          "Teléfono": "5551001",
          "Email": "benjamin.gonzalez@medisalud.com"
     }
  ```

### 2. Gestión de Pacientes

#### Registrar un Paciente
Registra un nuevo paciente validando formato de email, documento y teléfono.

* **URL:** `/api/v1/patients`
* **Método:** `POST`
* **Body Request (`PatientRequest`):**
  ```json
  {
    "name": "Juan Pérez",
    "identificationNumber": "12345678",
    "phoneNumber": "5559999",
    "email": "juan.perez@email.com",
    "birthDay": "1990-10-10"
  }
  ```
* **Success Response (201 Created) (`PatientResponse`):**
  ```json
     {
          "Id": "744d56e0-bc30-4995-9288-0254d87366c3",
          "Nombre": "Juan Pérez",
          "Identificación": "12345678",
          "Teléfono": "5559999",
          "Email": "juan.perez@email.com",
          "Fecha de nacimiento": "1990-10-10"
     }
  ```

### 3. Gestión de Citas

#### Reservar una Cita
Permite a un paciente agendar una cita con un médico disponible utilizando la identificación del paciente.

* **URL:** `/api/v1/appointments`
* **Método:** `POST`
* **Body Request (`AppointmentRequest`):**
  ```json
     {
          "patientIdentification": "123456789",
          "doctorId": "d1259a02-575a-43aa-a9ad-c7d6d760a454",
          "appointmentDate": "2026-07-29T13:30:00"
     }
  ```
* **Success Response (201 Created) (`AppointmentResponse`):**
  ```json
     {
          "Id": "9092ab6d-8faf-4ffa-bd89-6e11786e3657",
          "Paciente": "Louise",
          "Médico": "Dr. Carlos Pavon",
          "Fecha": "2026-07-29T13:30:00",
          "Estado": "PROGRAMADA"
     }
  ```

#### Consultar Horarios Disponibles
Consulta las franjas horarias disponibles de un médico específico en un rango de fechas.

* **URL:** `/api/v1/appointments/doctor/{doctorId}?startDate=2026-06-15T08:00:00&endDate=2026-06-15T18:00:00`
* **Método:** `GET`
* **Success Response (200 OK) (`List<AppointmentScheduleResponse>`):**
  ```json
  
  [
      {
          "Fecha": "2026-06-30",
          "HorariosDisponibles": [
              "08:00:00",
              "08:30:00",
              "09:00:00",
              "09:30:00",
              "10:00:00",
              "10:30:00",
              "11:00:00",
              "11:30:00",
              "12:00:00",
              "12:30:00",
              "13:00:00",
              "13:30:00",
              "14:00:00",
              "14:30:00",
              "15:00:00",
              "15:30:00",
              "16:00:00",
              "16:30:00",
              "17:00:00",
              "17:30:00"
          ]
      }
  ]

  ```

#### Cancelar Cita
Cancela una cita programada. El sistema evaluará automáticamente si aplica penalización.

* **URL:** `/api/v1/appointments/cancel`
* **Método:** `PATCH`
* **Body Request (`AppointmentCancelRequest`):**
  ```json
  {
    "appointmentId": "555e1234-e89b-12d3-a456-426614174222"
  }
  ```
* **Success Response (200 OK) (`AppointmentResponse`):**
  ```json
  {
    "Id": "555e1234-e89b-12d3-a456-426614174222",
    "Paciente": "Juan Pérez",
    "Médico": "Dra. María González",
    "Fecha": "2026-06-15T09:00:00",
    "Fecha de cancelación": "2026-06-14T10:00:00",
    "Estado": "CANCELADA"
  }
  ```

#### Listar Citas (Filtros Opcionales)
Lista las citas médicas registradas en el sistema. Permite filtrar los resultados de manera opcional por médico, paciente, estado o rango de fechas.

* **URL:** `/api/v1/appointments`
* **Método:** `GET`
* **Parámetros de Query Opcionales:** `doctorId`, `patientId`, `status`, `startDate`, `endDate`
* **Ejemplo de Petición:** `/api/v1/appointments?status=PROGRAMADA&doctorId=123e4567-e89b-12d3-a456-426614174000`
* **Success Response (200 OK) (`List<AppointmentResponse>`):**
  ```json
  [
    {
      "Id": "555e1234-e89b-12d3-a456-426614174222",
      "Paciente": "Juan Pérez",
      "Médico": "Dra. María González",
      "Fecha": "2026-06-15T09:00:00",
      "Estado": "PROGRAMADA"
    },
    {
      "Id": "888e1234-e89b-12d3-a456-426614174999",
      "Paciente": "Ana López",
      "Médico": "Dra. María González",
      "Fecha": "2026-06-15T10:30:00",
      "Estado": "PROGRAMADA"
    }
  ]
  ```

#### Reprogramar Cita
Reprograma una cita existente a un nuevo horario disponible.

* **URL:** `/api/v1/appointments/reschedule`
* **Método:** `PATCH`
* **Body Request (`AppointmentRescheduleRequest`):**
  ```json
  {
    "appointmentId": "555e1234-e89b-12d3-a456-426614174222",
    "newAppointmentDate": "2026-06-16T10:00:00"
  }
  ```
* **Success Response (200 OK) (`AppointmentResponse`):**
  ```json
  {
    "Id": "777e9999-e89b-12d3-a456-426614174333",
    "Paciente": "Juan Pérez",
    "Médico": "Dra. María González",
    "Fecha": "2026-06-16T10:00:00",
    "Estado": "PROGRAMADA"
  }
  ```