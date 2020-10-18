package com.timetracking.timetrackingapi.service.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

public class DataUtil {

    public static final DateTimeFormatter DATE_TIME_FORMAT;
    public static final DateTimeFormatter DATE_FORMAT;

    static {
        DATE_TIME_FORMAT = obterSimpleDateFormat("dd/MM/yyyy HH:mm");
        DATE_FORMAT = obterSimpleDateFormat("dd/MM/yyyy");
    }

    public static DateTimeFormatter obterSimpleDateFormat(final String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static long calcularMinutosEntreDuasDatas(LocalDateTime data1, LocalDateTime data2) {
        return data1.until(data2, ChronoUnit.MINUTES);
    }

    public static boolean ehFinalDeSemana(LocalDateTime data) {
        DayOfWeek d = data.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }

    public static LocalDateTime transformarStringDataHoraParaLocalDateTime(String data) {
        if (isNull(data))
            return null;
        return LocalDateTime.parse(data, DATE_TIME_FORMAT);
    }

    public static LocalDateTime transformarStringParaLocalDateTimePorPattern(String data, String pattern) {
        if (isNull(data))
            return null;
        return LocalDateTime.parse(data, obterSimpleDateFormat(pattern));
    }

    public static LocalDate transformarStringDataParaLocalDate(String data) {
        if (isNull(data))
            return null;
        return LocalDate.parse(data, DATE_FORMAT);
    }

    public static LocalDate retornarPrimeiroDiaDoMesPorLocalDate(LocalDate data) {
        if (isNull(data))
            return null;
        return data.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate retornarUltimoDiaDoMesPorLocalDate(LocalDate data) {
        if (isNull(data))
            return null;
        return data.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static Long transformarStringHorasEmMinutos(String tempoEmHora) {
        String[] partes = tempoEmHora.split(":");

        long hora = Long.parseLong(partes[0]);
        long min = Long.parseLong(partes[1]);

        return (hora * 60) + min;
    }

    public static Long transformarHorasSemMinutosEmMinutos(long horas) {
        return horas * 60;
    }

    public static String transformarMinutosEmStringHora(Long minutos) {
        long hours = TimeUnit.MINUTES.toHours(Long.valueOf(minutos));
        long remainMinutes = minutos - TimeUnit.HOURS.toMinutes(hours);
        return String.format("%02d:%02d", hours, remainMinutes);
    }

    public static long countDiasDaSemanaEntreDuasDatas(final LocalDate inicio, final LocalDate fim) {
        final DayOfWeek inicioS = inicio.getDayOfWeek();
        final DayOfWeek finalS = fim.getDayOfWeek();

        final long dias = ChronoUnit.DAYS.between(inicio, fim);
        final long diasSemFinaisDeSemana = dias - 2 * ((dias + inicioS.getValue())/7);

        return diasSemFinaisDeSemana + (inicioS == DayOfWeek.SUNDAY ? 1 : 0) + (finalS == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
