package com.timetracking.timetrackingapi.service.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

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

    public static LocalDate transformarStringDataParaLocalDateTime(String data) {
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
}
