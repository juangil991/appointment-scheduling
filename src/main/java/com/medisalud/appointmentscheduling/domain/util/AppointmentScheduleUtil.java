package com.medisalud.appointmentscheduling.domain.util;

import com.medisalud.appointmentscheduling.domain.constants.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppointmentScheduleUtil {

    public static List<LocalTime> generateTimeSlots(LocalDate date){

        DayOfWeek day = date.getDayOfWeek();

        if(day == DayOfWeek.SUNDAY){
            return Collections.emptyList();
        }

        LocalTime start;
        LocalTime end;

        if(day == DayOfWeek.SATURDAY){
            start = WorkingHours.SATURDAY_START;
            end = WorkingHours.SATURDAY_END;
        }else{
            start = WorkingHours.WEEKDAY_START;
            end = WorkingHours.WEEKDAY_END;
        }

        List<LocalTime> slots = new ArrayList<>();

        LocalTime current = start;

        while(current.isBefore(end)){
            slots.add(current);
            current = current.plus(WorkingHours.SLOT_DURATION);
        }

        return slots;
    }
}
