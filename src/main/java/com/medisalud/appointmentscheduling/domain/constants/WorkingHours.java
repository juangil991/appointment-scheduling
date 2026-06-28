package com.medisalud.appointmentscheduling.domain.constants;

import java.time.Duration;
import java.time.LocalTime;

public class WorkingHours {

    public static final LocalTime WEEKDAY_START = LocalTime.of(8,0);
    public static final LocalTime WEEKDAY_END = LocalTime.of(18,0);

    public static final LocalTime SATURDAY_START = LocalTime.of(8,0);
    public static final LocalTime SATURDAY_END = LocalTime.of(13,0);

    public static final Duration SLOT_DURATION = Duration.ofMinutes(30);
}
