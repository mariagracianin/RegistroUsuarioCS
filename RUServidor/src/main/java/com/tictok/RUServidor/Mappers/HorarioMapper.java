package com.tictok.RUServidor.Mappers;

import com.tictok.Commons.HorarioDTO;
import com.tictok.RUServidor.Entities.NotTables.Horario;
import com.tictok.RUServidor.Entities.NotTables.ServicioId;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class HorarioMapper {

    public static Horario fromHorarioDTOToHorario(HorarioDTO horarioDTO){
        int horaInicio = horarioDTO.getHoraInicio();
        int hora = horaInicio/100;
        LocalTime horaInicioId = LocalTime.of(hora, horaInicio-hora*100);
        int horaFin = horarioDTO.getHoraFin();
        hora = horaFin/100;
        LocalTime horaFinId = LocalTime.of(hora, horaFin-hora*100);
        DayOfWeek diaId = setearDia(horarioDTO.getDia());
        return new Horario(diaId, horaInicioId, horaFinId);
    }
    private static DayOfWeek setearDia(int numeroDia){
        DayOfWeek dia = DayOfWeek.MONDAY;
        switch (numeroDia) {
            case 1 -> dia = DayOfWeek.MONDAY;
            case 2 -> dia = DayOfWeek.TUESDAY;
            case 3 -> dia = DayOfWeek.WEDNESDAY;
            case 4 -> dia = DayOfWeek.THURSDAY;
            case 5 -> dia = DayOfWeek.FRIDAY;
            case 6 -> dia = DayOfWeek.SATURDAY;
            case 7 -> dia = DayOfWeek.SUNDAY;
        }
        return dia;
    }

    public static int getDia(DayOfWeek dia){
        int diaNumero = 0;
        switch (dia){
            case MONDAY -> diaNumero = 1;
            case TUESDAY -> diaNumero = 2;
            case WEDNESDAY -> diaNumero = 3;
            case THURSDAY -> diaNumero = 4;
            case FRIDAY -> diaNumero = 5;
            case SATURDAY -> diaNumero = 6;
            case SUNDAY -> diaNumero = 7;
        }
        return diaNumero;
    }

    public static LocalDate getFecha(DayOfWeek dia){
        LocalDate fecha = LocalDate.now();
        if (!fecha.getDayOfWeek().equals(dia)){
            fecha = fecha.with(TemporalAdjusters.next(dia));
        }
        return fecha;
    }

    public static HorarioDTO fromServicioIdtoHorarioDTO(ServicioId servicioId) {
        int dia = servicioId.getDia().getValue();
        int horaInicio = servicioId.getHoraInicio().getHour()*100 + servicioId.getHoraInicio().getMinute();
        int horaFin = servicioId.getHoraFin().getHour()*100 + servicioId.getHoraFin().getMinute();
        return new HorarioDTO(dia, horaInicio, horaFin);
    }

    public static int fromLocalTimeToIntHora(LocalTime hora){
        int hour = hora.getHour();
        int minutes = hora.getMinute();
        return hour*100 + minutes;
    }
}
