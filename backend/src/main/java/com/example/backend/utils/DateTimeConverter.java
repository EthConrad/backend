package com.example.backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DateTimeConverter {

    private static final List<DateTimeFormatter> dateFormatList = Arrays.asList(
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter().withLocale(Locale.ENGLISH),
            new DateTimeFormatterBuilder().appendPattern("yyyy/MM/dd[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter().withLocale(Locale.ENGLISH),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter().withLocale(Locale.ENGLISH),
        new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy[ HH:mm]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter().withLocale(Locale.ENGLISH)
    );

    private DateTimeConverter() {
    }

    public static LocalDateTime convertDateTime(String inputDateTime) throws IllegalArgumentException{
        LocalDateTime date = null;
        for (DateTimeFormatter simpleDateFormat : dateFormatList) {
            try {
                date = LocalDateTime.parse(inputDateTime, simpleDateFormat);
                break;
            } catch (DateTimeParseException e) {
                // Do nothing as this is expected
            }
        }

        if (date == null) {
            throw new IllegalArgumentException("User input string could not be converted to a valid date. " +
                    "Please try altering the date to be in another format such as dd/MM/yyyy or yyyy/MM/dd HH:mm");
        }
        return date;
    }
}
